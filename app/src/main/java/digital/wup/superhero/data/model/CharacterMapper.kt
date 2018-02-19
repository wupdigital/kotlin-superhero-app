package digital.wup.superhero.data.model

import digital.wup.superhero.data.model.local.CharacterDto


fun Character.toDto(): CharacterDto {
    val character: Character = this
    return CharacterDto(0, character.id.toString(), character.name, character.description, character.thumbnail!!.path, character.thumbnail!!.extension)
}

fun CharacterDto.to(): Character {
    val characterDto: CharacterDto = this
    return Character(characterDto.characterId.toLong(), characterDto.name, characterDto.description, Image(characterDto.path, characterDto.extension))
}