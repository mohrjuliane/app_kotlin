package fhs.mmt.nma.pixie.ui.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import fhs.mmt.nma.pixie.data.Photographer
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.AllPosts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.lang.IllegalStateException
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*


class HomeViewModel(state: SavedStateHandle) : ViewModel() {
    //val posts: List<Post> = AllPosts
    val posts: List<Post> = state.get("posts") ?: throw IllegalStateException("Missing argument")

    private val _uiState =  MutableStateFlow(HomeScreenUiState(loading = true))
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(loading = false, post = posts)
        }
        viewModelScope.launch {
            _event.collect {
                when (it) {
                    is Event.onUserClicked -> {
                        setEffect { Effect.NavigateToUser(it.userId)}
                    }
                }
            }
        }
        //da drinnen posts laden
        //when it userprofile.clicked, setEffect
    }

    private val _effect: Channel<Event> = Channel()
    val effect = _effect.receiveAsFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

    fun setEvent(event: Event) {

    }

    fun setEffect() {

    }

    fun onUserClicked(author : Photographer, navController: NavController) {
        navController.navigate(route = "profile/${author.id}")
    }
}

data class HomeScreenUiState(
    val loading: Boolean,
    val post: List<Post> = emptyList()
)

sealed class Event {
    data class onUserClicked(val userId: Int) : Event()
}

sealed class Effect {
    data class NavigateToUser(val userId: Int) : Event()
}

