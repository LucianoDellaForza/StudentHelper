package rs.raf.studenthelper.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.studenthelper.data.datasources.local.DataBase
import rs.raf.studenthelper.data.datasources.remote.SubjectService
import rs.raf.studenthelper.data.repositories.SubjectRepository
import rs.raf.studenthelper.data.repositories.SubjectRepositoryImpl
import rs.raf.studenthelper.presentation.viewmodel.SubjectViewModel

val subjectModule = module {

    viewModel { SubjectViewModel ( subjectRepository =  get()) }

    single<SubjectRepository> { SubjectRepositoryImpl(localDataSource = get(), remoteDataSource = get())}

    single { get<DataBase>().getSubjectDao() }

    single<SubjectService> { create(retrofit = get()) }
}