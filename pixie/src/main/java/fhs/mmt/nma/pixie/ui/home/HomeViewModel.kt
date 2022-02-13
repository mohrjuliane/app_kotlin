package fhs.mmt.nma.pixie.ui.home

import androidx.lifecycle.ViewModel
import fhs.mmt.nma.pixie.data.Post
import fhs.mmt.nma.pixie.samples.AllPosts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*


class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState(loading = true))
    val uiState = _uiState.asStateFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

    fun setEvent(event: Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    private fun setEffect(builder: () -> Effect) {
        viewModelScope.launch {
            val effectValue = builder()
            _effect.send(effectValue)
        }
    }

    init {
        viewModelScope.launch {
            _uiState.value = HomeScreenUiState(loading = false, posts = AllPosts)
            _event.collect {
                when (it) {
                    is Event.OnUserClicked -> {
                        setEffect { Effect.NavigateToUser(it.userId) }
                    }
                }
            }
        }
    }
}

data class HomeScreenUiState(
    val loading: Boolean,
    val posts: List<Post> = emptyList()
)

sealed class Event {
    data class OnUserClicked(val userId: Int) : Event()
}

sealed class Effect {
    data class NavigateToUser(val userId: Int) : Effect()
}

