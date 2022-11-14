package com.example.reegoandroid.viewmodels

import androidx.lifecycle.ViewModel
import com.example.reegoandroid.viewmodels.node.NodeApi
import com.example.reegoandroid.viewmodels.node.NodeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SingleNoteViewModel (private val nodeRepository: NodeRepository = NodeRepository(NodeApi.instance!!)): ViewModel() {
    init {

    }

    internal fun deleteComment(commentId: Int) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {


            val result = nodeRepository.deleteComment(commentId);

            result.onSuccess {
                println("Comentario Borrado $commentId" )
            }.onFailure {
                println("Error en a llamada al api | Delete comment")

            }

        }
    }

    internal fun updateComment(commentId: Int, commentText: String) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {

            val result = nodeRepository.updateComment(commentId, commentText);

            result.onSuccess {
                println("Comentario Actualizado $commentId" )
            }.onFailure {
                println("Error en a llamada al api - Update Comment")
                println(result.toString())
                println(it.message)
            }

        }
    }

    // singleNoteViewModel.createComment(newText,irrigationId)
    internal fun createComment(commentText: String, irrigationId: Int) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {

            val result = nodeRepository.createComment(commentText, irrigationId);

            result.onSuccess {
                println("Comentario Creado" )
            }.onFailure {
                println("Error en a llamada al api - Create Comment")
            }

        }
    }

}