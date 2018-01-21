package cn.wangliang.androidbasis.ui.mvp;

public interface MvpPresenter<T extends MvpView> {
    void attachView(T view);
    void detachView();
}