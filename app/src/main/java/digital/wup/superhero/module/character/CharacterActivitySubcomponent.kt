package digital.wup.superhero.module.character

import dagger.Subcomponent
import dagger.android.AndroidInjector
import digital.wup.superhero.presentation.ui.characters.CharactersActivity

@Subcomponent
interface CharacterActivitySubcomponent : AndroidInjector<CharactersActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<CharactersActivity>()
}
