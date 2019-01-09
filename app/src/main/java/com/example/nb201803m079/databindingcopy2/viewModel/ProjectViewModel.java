package com.example.nb201803m079.databindingcopy2.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.example.nb201803m079.databindingcopy2.service.model.Project;
import com.example.nb201803m079.databindingcopy2.service.repository.ProjectRepository;

public class ProjectViewModel extends AndroidViewModel {

    private final LiveData<Project> projeceObservable;
    private final String projectID;

    public ObservableField<Project> project = new ObservableField<>();

    public ProjectViewModel(@NonNull Application application, final String projectID) {
        super(application);
        this.projectID = projectID;
        projeceObservable = ProjectRepository.getInstance().getProjectDetails("Tsutou", projectID);
    }

    public LiveData<Project> getObservalProject() {
        return projeceObservable;
    }

    public void setProject(ObservableField<Project> project) {
        this.project = project;
    }


//    ViewModelProviderを継承したFactory
//    ViewModelはUIに関連したデータを保持･管理している。
//    ViewModelのインスタンスはViewModelProvider経由で取得する(下のFactoryは、ViewModelのインスタンスを作成する別の手段？)

//    Factoryが何をしているかよくわからない。依存性の注入だから、外から値が入るようす？ただ、どうやって値を入れているのか？
// 　　



    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final Application application;
        private final String projectID;

        public Factory(@NonNull Application application, String projectID) {
            this.application = application;
            this.projectID = projectID;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ProjectViewModel(application, projectID)
        }
    }
}