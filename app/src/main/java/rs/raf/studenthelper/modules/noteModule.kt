package rs.raf.studenthelper.modules

import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import rs.raf.studenthelper.data.datasources.local.DataBase
import rs.raf.studenthelper.data.repositories.NoteRepository
import rs.raf.studenthelper.data.repositories.NoteRepositoryImpl
import rs.raf.studenthelper.presentation.viewmodel.NoteViewModel

val noteModule = module {

    viewModel { NoteViewModel ( noteRepository =  get()) }

    single<NoteRepository> { NoteRepositoryImpl(localDataSource = get())}

    single { get<DataBase>().getNoteDao() }

}