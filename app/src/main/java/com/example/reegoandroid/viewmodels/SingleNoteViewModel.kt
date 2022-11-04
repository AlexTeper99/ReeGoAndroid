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

    // TODO Delete comment by id

//    internal fun deleteComment(commentId: Int) {
//        val scope = CoroutineScope(Dispatchers.Default)
//        scope.launch {
//
//
//            val result = nodeRepository.deleteComment(commentId);
//
//            result.onSuccess {
//                println("Comentario Borrado $commentId" )
//            }.onFailure {
//                println("Error en a llamada al api / delete comment")
//            }
//
//        }
//    }

    internal fun updateComment(commentId: Int, commentText: String) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {

            val result = nodeRepository.updateComment(commentId, commentText);

            result.onSuccess {
                println("Comentario Actualizado $commentId" )
            }.onFailure {
                println("Error en a llamada al api - Update Comment")
            }

        }
    }

}