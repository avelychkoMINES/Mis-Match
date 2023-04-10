package com.csci448.avelychko.mis_match.data.database

import com.csci448.avelychko.mis_match.R

data class Picture(val imageId: Int,
              val nameId: Int, val descId: Int)

object PictureRepo {
  val topList = listOf(
      Picture(imageId = R.drawable.monsters_university_character_young_mike_icons,
            nameId = R.string.name_monster_mike,
            descId = R.string.description_monster_mike),
      Picture(imageId =
        R.drawable.monsters_university_james_p_sullivan_icon,
            nameId = R.string.name_monster_james_p_sullivan,
            descId = R.string.description_monster_james_p_sullivan),
      Picture(imageId =
        R.drawable.monsters_university_character_professor_knight_icon,
            nameId = R.string.name_monster_professor_knight,
            descId = R.string.description_monster_professor_knight),
      Picture(imageId =
        R.drawable.monsters_university_character_randy_boggs_icon,
            nameId = R.string.name_monster_randy_boggs,
            descId = R.string.description_monster_randy_boggs),
      Picture(imageId =
        R.drawable.monsters_university_don_carlton_icon,
            nameId = R.string.name_monster_don_carlton,
            descId = R.string.description_monster_don_carlton),
      Picture(imageId = R.drawable.monsters_university_art_icon,
            nameId = R.string.name_monster_art,
            descId = R.string.description_monster_art),
      Picture(imageId =
        R.drawable.monsters_university_squishy_running_icon,
            nameId = R.string.name_monster_squishy,
            descId = R.string.description_monster_squishy),
      Picture(imageId =
        R.drawable.monsters_university_character_ms_squibbles_icon,
            nameId = R.string.name_monster_ms_squibbles,
            descId = R.string.description_monster_ms_squibbles),
      Picture(imageId =
        R.drawable.monsters_university_johnny_worthington_icon,
            nameId = R.string.name_monster_johnny_worthington,
            descId = R.string.description_monster_johnny_worthington),
      Picture(imageId = R.drawable.monsters_university_mascot_icon,
            nameId = R.string.name_mascot_archie,
            descId = R.string.name_mascot_archie),
      Picture(imageId = R.drawable.monsters_university_terry_icon,
            nameId = R.string.name_monster_terri_terry,
            descId = R.string.description_monster_terri_terry),
      Picture(imageId =
        R.drawable.monsters_university_villain_dean_hardscrabble_icon,
            nameId = R.string.name_villian_dean_hardscrabble,
            descId = R.string.description_villian_dean_hardscrabble)
    )

    val bottomList = listOf(
        Picture(imageId = R.drawable.monsters_university_character_young_mike_icons,
            nameId = R.string.name_monster_mike,
            descId = R.string.description_monster_mike),
        Picture(imageId =
        R.drawable.monsters_university_james_p_sullivan_icon,
            nameId = R.string.name_monster_james_p_sullivan,
            descId = R.string.description_monster_james_p_sullivan),
        Picture(imageId =
        R.drawable.monsters_university_character_professor_knight_icon,
            nameId = R.string.name_monster_professor_knight,
            descId = R.string.description_monster_professor_knight),
        Picture(imageId =
        R.drawable.monsters_university_character_randy_boggs_icon,
            nameId = R.string.name_monster_randy_boggs,
            descId = R.string.description_monster_randy_boggs),
        Picture(imageId =
        R.drawable.monsters_university_don_carlton_icon,
            nameId = R.string.name_monster_don_carlton,
            descId = R.string.description_monster_don_carlton),
        Picture(imageId = R.drawable.monsters_university_art_icon,
            nameId = R.string.name_monster_art,
            descId = R.string.description_monster_art),
        Picture(imageId =
        R.drawable.monsters_university_squishy_running_icon,
            nameId = R.string.name_monster_squishy,
            descId = R.string.description_monster_squishy),
        Picture(imageId =
        R.drawable.monsters_university_character_ms_squibbles_icon,
            nameId = R.string.name_monster_ms_squibbles,
            descId = R.string.description_monster_ms_squibbles),
        Picture(imageId =
        R.drawable.monsters_university_johnny_worthington_icon,
            nameId = R.string.name_monster_johnny_worthington,
            descId = R.string.description_monster_johnny_worthington),
        Picture(imageId = R.drawable.monsters_university_mascot_icon,
            nameId = R.string.name_mascot_archie,
            descId = R.string.name_mascot_archie),
        Picture(imageId = R.drawable.monsters_university_terry_icon,
            nameId = R.string.name_monster_terri_terry,
            descId = R.string.description_monster_terri_terry),
        Picture(imageId =
        R.drawable.monsters_university_villain_dean_hardscrabble_icon,
            nameId = R.string.name_villian_dean_hardscrabble,
            descId = R.string.description_villian_dean_hardscrabble)
    )

    val shoesList = listOf(
        Picture(imageId = R.drawable.monsters_university_character_young_mike_icons,
            nameId = R.string.name_monster_mike,
            descId = R.string.description_monster_mike),
        Picture(imageId =
        R.drawable.monsters_university_james_p_sullivan_icon,
            nameId = R.string.name_monster_james_p_sullivan,
            descId = R.string.description_monster_james_p_sullivan),
        Picture(imageId =
        R.drawable.monsters_university_character_professor_knight_icon,
            nameId = R.string.name_monster_professor_knight,
            descId = R.string.description_monster_professor_knight),
        Picture(imageId =
        R.drawable.monsters_university_character_randy_boggs_icon,
            nameId = R.string.name_monster_randy_boggs,
            descId = R.string.description_monster_randy_boggs),
        Picture(imageId =
        R.drawable.monsters_university_don_carlton_icon,
            nameId = R.string.name_monster_don_carlton,
            descId = R.string.description_monster_don_carlton),
        Picture(imageId = R.drawable.monsters_university_art_icon,
            nameId = R.string.name_monster_art,
            descId = R.string.description_monster_art),
        Picture(imageId =
        R.drawable.monsters_university_squishy_running_icon,
            nameId = R.string.name_monster_squishy,
            descId = R.string.description_monster_squishy),
        Picture(imageId =
        R.drawable.monsters_university_character_ms_squibbles_icon,
            nameId = R.string.name_monster_ms_squibbles,
            descId = R.string.description_monster_ms_squibbles),
        Picture(imageId =
        R.drawable.monsters_university_johnny_worthington_icon,
            nameId = R.string.name_monster_johnny_worthington,
            descId = R.string.description_monster_johnny_worthington),
        Picture(imageId = R.drawable.monsters_university_mascot_icon,
            nameId = R.string.name_mascot_archie,
            descId = R.string.name_mascot_archie),
        Picture(imageId = R.drawable.monsters_university_terry_icon,
            nameId = R.string.name_monster_terri_terry,
            descId = R.string.description_monster_terri_terry),
        Picture(imageId =
        R.drawable.monsters_university_villain_dean_hardscrabble_icon,
            nameId = R.string.name_villian_dean_hardscrabble,
            descId = R.string.description_villian_dean_hardscrabble)
    )
}
