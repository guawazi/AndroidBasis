package cn.wangliang.androidbasis.data.network;

import java.net.SocketTimeoutException;

import cn.wangliang.androidbasis.R;
import cn.wangliang.androidbasis.data.network.exception.ApiException;
import cn.wangliang.androidbasis.data.network.okhttputils.NetworkUtil;
import cn.wangliang.androidbasis.util.Constant;
import cn.wangliang.androidbasis.util.CommonUtils;
import io.reactivex.observers.DisposableObserver;


public abstract class RxObserver<T> extends DisposableObserver<T> {

    private BaseView mView;
    private String mMsg;
    private boolean isShowDialog;

    public RxObserver(BaseView view, String msg, boolean showDialog) {
        this.mView = view;
        this.mMsg = msg;
        this.isShowDialog = showDialog;
    }

    public RxObserver(BaseView view){
        this(view, CommonUtils.getString(R.string.api_loading), true);
    }

    public RxObserver(BaseView view, boolean showDialog){
        this(view, CommonUtils.getString(R.string.api_loading), showDialog);
    }

    @Override
    public void onStart() {
        if(mView == null || mView.isFinished()) return;
        if(isShowDialog) mView.stateLoading(mMsg);
    }

    @Override
    public void onNext(T bean) {
        if(mView == null || mView.isFinished()) return;
        mView.stateMain();
        onSuccess(bean);
    }

    @Override
    public void onError(Throwable e) {
        if(mView == null || mView.isFinished()) return;
        ResultBean bean;
        if(!NetworkUtil.isConnected()){
            bean = new ResultBean(Constant.STATUS_DISCONNECT, CommonUtils.getString(R.string.api_net_disable));
        }else if(e instanceof ApiException){
            bean = ((ApiException) e).getBean();
        }else if(e instanceof SocketTimeoutException){
            bean = new ResultBean(Constant.STATUS_TIMEOUT, CommonUtils.getString(R.string.api_net_timeout));
        }else {
            bean = new ResultBean(Constant.STATUS_ERROR, CommonUtils.getString(R.string.api_net_error));
        }
        mView.stateError(bean);
        onFailed(bean);
    }

    /**
     * 成功回调方法
     */
    public abstract void onSuccess(T bean);

    /**
     * 失败回调方法
     */
    public void onFailed(ResultBean bean) {
        mView.showErrorMsg(bean.getMsg());
    }

    @Override
    public void onComplete() {
        RxFlowable.disposable(this);
    }

}
