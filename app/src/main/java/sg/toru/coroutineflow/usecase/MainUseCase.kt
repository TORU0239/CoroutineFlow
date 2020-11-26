package sg.toru.coroutineflow.usecase

import sg.toru.coroutineflow.repository.MainRepository

class MainUseCase(private val repository: MainRepository) {
    fun getData(){
        repository.getData()
    }
}