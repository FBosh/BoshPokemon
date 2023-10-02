package com.boshpokemon.models.entities

import com.boshpokemon.App
import com.boshpokemon.R
import com.boshpokemon.models.BaseModel

class Type(
        private val id: Int,
        val name: String,
        val colorRes: Int = 0,
        val colorLightRes: Int = 0,
        val colorDarkRes: Int = 0,
        val superEffectiveTypes: ArrayList<Type> = arrayListOf(),
        val notVeryEffectiveTypes: ArrayList<Type> = arrayListOf(),
        val notEffectiveTypes: ArrayList<Type> = arrayListOf(),
        val beSuperEffectiveTypes: ArrayList<Type> = arrayListOf(),
        val beNotVeryEffectiveTypes: ArrayList<Type> = arrayListOf(),
        val beNotEffectiveTypes: ArrayList<Type> = arrayListOf()
) : BaseModel() {
    companion object {
        private val appCtx by lazy { App.shared().applicationContext ?: App.shared() }

        val typeUnknown by lazy { Type(-1, appCtx.getString(R.string.type_unknown)) }

        val typeNormal by lazy {
            Type(
                    0,
                    appCtx.getString(R.string.type_normal),
                    R.color.color_type_normal,
                    R.color.color_type_normal_light,
                    R.color.color_type_normal_dark
            )
        }

        val typeFighting by lazy {
            Type(
                    1,
                    appCtx.getString(R.string.type_fighting),
                    R.color.color_type_fighting,
                    R.color.color_type_fighting_light,
                    R.color.color_type_fighting_dark
            )
        }

        val typeFlying by lazy {
            Type(
                    2,
                    appCtx.getString(R.string.type_flying),
                    R.color.color_type_flying,
                    R.color.color_type_flying_light,
                    R.color.color_type_flying_dark
            )
        }

        val typePoison by lazy {
            Type(
                    3,
                    appCtx.getString(R.string.type_poison),
                    R.color.color_type_poison,
                    R.color.color_type_poison_light,
                    R.color.color_type_poison_dark
            )
        }

        val typeGround by lazy {
            Type(
                    4,
                    appCtx.getString(R.string.type_ground),
                    R.color.color_type_ground,
                    R.color.color_type_ground_light,
                    R.color.color_type_ground_dark
            )
        }

        val typeRock by lazy {
            Type(
                    5,
                    appCtx.getString(R.string.type_rock),
                    R.color.color_type_rock,
                    R.color.color_type_rock_light,
                    R.color.color_type_rock_dark
            )
        }
        val typeBug by lazy {
            Type(
                    6,
                    appCtx.getString(R.string.type_bug),
                    R.color.color_type_bug,
                    R.color.color_type_bug_light,
                    R.color.color_type_bug_dark
            )
        }

        val typeGhost by lazy {
            Type(
                    7,
                    appCtx.getString(R.string.type_ghost),
                    R.color.color_type_ghost,
                    R.color.color_type_ghost_light,
                    R.color.color_type_ghost_dark
            )
        }

        val typeSteel by lazy {
            Type(
                    8,
                    appCtx.getString(R.string.type_steel),
                    R.color.color_type_steel,
                    R.color.color_type_steel_light,
                    R.color.color_type_steel_dark
            )
        }

        val typeFire by lazy {
            Type(
                    9,
                    appCtx.getString(R.string.type_fire),
                    R.color.color_type_fire,
                    R.color.color_type_fire_light,
                    R.color.color_type_fire_dark
            )
        }

        val typeWater by lazy {
            Type(
                    10,
                    appCtx.getString(R.string.type_water),
                    R.color.color_type_water,
                    R.color.color_type_water_light,
                    R.color.color_type_water_dark
            )
        }

        val typeGrass by lazy {
            Type(
                    11,
                    appCtx.getString(R.string.type_grass),
                    R.color.color_type_grass,
                    R.color.color_type_grass_light,
                    R.color.color_type_grass_dark
            )
        }

        val typeElectric by lazy {
            Type(
                    12,
                    appCtx.getString(R.string.type_electric),
                    R.color.color_type_electric,
                    R.color.color_type_electric_light,
                    R.color.color_type_electric_dark
            )
        }

        val typePsychic by lazy {
            Type(
                    13,
                    appCtx.getString(R.string.type_psychic),
                    R.color.color_type_psychic,
                    R.color.color_type_psychic_light,
                    R.color.color_type_psychic_dark
            )
        }

        val typeIce by lazy {
            Type(
                    14,
                    appCtx.getString(R.string.type_ice),
                    R.color.color_type_ice,
                    R.color.color_type_ice_light,
                    R.color.color_type_ice_dark
            )
        }

        val typeDragon by lazy {
            Type(
                    15,
                    appCtx.getString(R.string.type_dragon),
                    R.color.color_type_dragon,
                    R.color.color_type_dragon_light,
                    R.color.color_type_dragon_dark
            )
        }

        val typeDark by lazy {
            Type(
                    16,
                    appCtx.getString(R.string.type_dark),
                    R.color.color_type_dark,
                    R.color.color_type_dark_light,
                    R.color.color_type_dark_dark
            )
        }

        val typeFairy by lazy {
            Type(
                    17,
                    appCtx.getString(R.string.type_fairy),
                    R.color.color_type_fairy,
                    R.color.color_type_fairy_light,
                    R.color.color_type_fairy_dark
            )
        }

        init {
            typeNormal.notVeryEffectiveTypes.add(typeRock)
            typeNormal.notVeryEffectiveTypes.add(typeSteel)
            typeNormal.notEffectiveTypes.add(typeGhost)
            typeNormal.beSuperEffectiveTypes.add(typeFighting)
            typeNormal.beNotEffectiveTypes.add(typeGhost)

            typeFighting.superEffectiveTypes.add(typeNormal)
            typeFighting.superEffectiveTypes.add(typeRock)
            typeFighting.superEffectiveTypes.add(typeSteel)
            typeFighting.superEffectiveTypes.add(typeIce)
            typeFighting.superEffectiveTypes.add(typeDark)
            typeFighting.notVeryEffectiveTypes.add(typeFlying)
            typeFighting.notVeryEffectiveTypes.add(typePoison)
            typeFighting.notVeryEffectiveTypes.add(typeBug)
            typeFighting.notVeryEffectiveTypes.add(typePsychic)
            typeFighting.notVeryEffectiveTypes.add(typeFairy)
            typeFighting.notEffectiveTypes.add(typeGhost)
            typeFighting.beSuperEffectiveTypes.add(typeFlying)
            typeFighting.beSuperEffectiveTypes.add(typePsychic)
            typeFighting.beSuperEffectiveTypes.add(typeFairy)
            typeFighting.beNotVeryEffectiveTypes.add(typeBug)
            typeFighting.beNotVeryEffectiveTypes.add(typeRock)
            typeFighting.beNotVeryEffectiveTypes.add(typeDark)

            typeFlying.superEffectiveTypes.add(typeFighting)
            typeFlying.superEffectiveTypes.add(typeBug)
            typeFlying.superEffectiveTypes.add(typeGrass)
            typeFlying.notVeryEffectiveTypes.add(typeRock)
            typeFlying.notVeryEffectiveTypes.add(typeSteel)
            typeFlying.notVeryEffectiveTypes.add(typeElectric)
            typeFlying.beSuperEffectiveTypes.add(typeRock)
            typeFlying.beSuperEffectiveTypes.add(typeElectric)
            typeFlying.beSuperEffectiveTypes.add(typeIce)
            typeFlying.beNotVeryEffectiveTypes.add(typeFighting)
            typeFlying.beNotVeryEffectiveTypes.add(typeBug)
            typeFlying.beNotVeryEffectiveTypes.add(typeGrass)
            typeFlying.beNotEffectiveTypes.add(typeGround)

            typePoison.superEffectiveTypes.add(typeGrass)
            typePoison.superEffectiveTypes.add(typeFairy)
            typePoison.notVeryEffectiveTypes.add(typePoison)
            typePoison.notVeryEffectiveTypes.add(typeGround)
            typePoison.notVeryEffectiveTypes.add(typeRock)
            typePoison.notVeryEffectiveTypes.add(typeGhost)
            typePoison.notEffectiveTypes.add(typeSteel)
            typePoison.beSuperEffectiveTypes.add(typeGround)
            typePoison.beSuperEffectiveTypes.add(typePsychic)
            typePoison.beNotVeryEffectiveTypes.add(typeFighting)
            typePoison.beNotVeryEffectiveTypes.add(typePoison)
            typePoison.beNotVeryEffectiveTypes.add(typeBug)
            typePoison.beNotVeryEffectiveTypes.add(typeGrass)
            typePoison.beNotVeryEffectiveTypes.add(typeFairy)

            typeGround.superEffectiveTypes.add(typePoison)
            typeGround.superEffectiveTypes.add(typeRock)
            typeGround.superEffectiveTypes.add(typeSteel)
            typeGround.superEffectiveTypes.add(typeFire)
            typeGround.superEffectiveTypes.add(typeElectric)
            typeGround.notVeryEffectiveTypes.add(typeBug)
            typeGround.notVeryEffectiveTypes.add(typeGrass)
            typeGround.notEffectiveTypes.add(typeFlying)
            typeGround.beSuperEffectiveTypes.add(typeWater)
            typeGround.beSuperEffectiveTypes.add(typeGrass)
            typeGround.beSuperEffectiveTypes.add(typeIce)
            typeGround.beNotVeryEffectiveTypes.add(typePoison)
            typeGround.beNotVeryEffectiveTypes.add(typeRock)
            typeGround.beNotEffectiveTypes.add(typeElectric)

            typeRock.superEffectiveTypes.add(typeFlying)
            typeRock.superEffectiveTypes.add(typeBug)
            typeRock.superEffectiveTypes.add(typeFire)
            typeRock.superEffectiveTypes.add(typeIce)
            typeRock.notVeryEffectiveTypes.add(typeFighting)
            typeRock.notVeryEffectiveTypes.add(typeGround)
            typeRock.notVeryEffectiveTypes.add(typeSteel)
            typeRock.beSuperEffectiveTypes.add(typeFighting)
            typeRock.beSuperEffectiveTypes.add(typeGround)
            typeRock.beSuperEffectiveTypes.add(typeSteel)
            typeRock.beSuperEffectiveTypes.add(typeWater)
            typeRock.beSuperEffectiveTypes.add(typeGrass)
            typeRock.beNotVeryEffectiveTypes.add(typeNormal)
            typeRock.beNotVeryEffectiveTypes.add(typeFlying)
            typeRock.beNotVeryEffectiveTypes.add(typePoison)
            typeRock.beNotVeryEffectiveTypes.add(typeFire)

            typeBug.superEffectiveTypes.add(typeGrass)
            typeBug.superEffectiveTypes.add(typePsychic)
            typeBug.superEffectiveTypes.add(typeDark)
            typeBug.notVeryEffectiveTypes.add(typeFighting)
            typeBug.notVeryEffectiveTypes.add(typeFlying)
            typeBug.notVeryEffectiveTypes.add(typePoison)
            typeBug.notVeryEffectiveTypes.add(typeGhost)
            typeBug.notVeryEffectiveTypes.add(typeSteel)
            typeBug.notVeryEffectiveTypes.add(typeFire)
            typeBug.notVeryEffectiveTypes.add(typeFairy)
            typeBug.beSuperEffectiveTypes.add(typeFlying)
            typeBug.beSuperEffectiveTypes.add(typeRock)
            typeBug.beSuperEffectiveTypes.add(typeFire)
            typeBug.beNotVeryEffectiveTypes.add(typeFighting)
            typeBug.beNotVeryEffectiveTypes.add(typeGround)
            typeBug.beNotVeryEffectiveTypes.add(typeGrass)

            typeGhost.superEffectiveTypes.add(typeGhost)
            typeGhost.superEffectiveTypes.add(typePsychic)
            typeGhost.notVeryEffectiveTypes.add(typeDark)
            typeGhost.notEffectiveTypes.add(typeNormal)
            typeGhost.beSuperEffectiveTypes.add(typeGhost)
            typeGhost.beSuperEffectiveTypes.add(typeDark)
            typeGhost.beNotVeryEffectiveTypes.add(typePoison)
            typeGhost.beNotVeryEffectiveTypes.add(typeBug)
            typeGhost.beNotEffectiveTypes.add(typeNormal)
            typeGhost.beNotEffectiveTypes.add(typeFighting)

            typeSteel.superEffectiveTypes.add(typeRock)
            typeSteel.superEffectiveTypes.add(typeIce)
            typeSteel.superEffectiveTypes.add(typeFairy)
            typeSteel.notVeryEffectiveTypes.add(typeSteel)
            typeSteel.notVeryEffectiveTypes.add(typeFire)
            typeSteel.notVeryEffectiveTypes.add(typeWater)
            typeSteel.notVeryEffectiveTypes.add(typeElectric)
            typeSteel.beSuperEffectiveTypes.add(typeFighting)
            typeSteel.beSuperEffectiveTypes.add(typeGround)
            typeSteel.beSuperEffectiveTypes.add(typeFire)
            typeSteel.beNotVeryEffectiveTypes.add(typeNormal)
            typeSteel.beNotVeryEffectiveTypes.add(typeFlying)
            typeSteel.beNotVeryEffectiveTypes.add(typeRock)
            typeSteel.beNotVeryEffectiveTypes.add(typeBug)
            typeSteel.beNotVeryEffectiveTypes.add(typeSteel)
            typeSteel.beNotVeryEffectiveTypes.add(typeGrass)
            typeSteel.beNotVeryEffectiveTypes.add(typePsychic)
            typeSteel.beNotVeryEffectiveTypes.add(typeIce)
            typeSteel.beNotVeryEffectiveTypes.add(typeDragon)
            typeSteel.beNotVeryEffectiveTypes.add(typeFairy)
            typeSteel.beNotEffectiveTypes.add(typePoison)

            typeFire.superEffectiveTypes.add(typeBug)
            typeFire.superEffectiveTypes.add(typeSteel)
            typeFire.superEffectiveTypes.add(typeGrass)
            typeFire.superEffectiveTypes.add(typeIce)
            typeFire.notVeryEffectiveTypes.add(typeRock)
            typeFire.notVeryEffectiveTypes.add(typeFire)
            typeFire.notVeryEffectiveTypes.add(typeWater)
            typeFire.notVeryEffectiveTypes.add(typeDragon)
            typeFire.beSuperEffectiveTypes.add(typeGround)
            typeFire.beSuperEffectiveTypes.add(typeRock)
            typeFire.beSuperEffectiveTypes.add(typeWater)
            typeFire.beNotVeryEffectiveTypes.add(typeBug)
            typeFire.beNotVeryEffectiveTypes.add(typeSteel)
            typeFire.beNotVeryEffectiveTypes.add(typeFire)
            typeFire.beNotVeryEffectiveTypes.add(typeGrass)
            typeFire.beNotVeryEffectiveTypes.add(typeIce)
            typeFire.beNotVeryEffectiveTypes.add(typeFairy)

            typeWater.superEffectiveTypes.add(typeGround)
            typeWater.superEffectiveTypes.add(typeRock)
            typeWater.superEffectiveTypes.add(typeFire)
            typeWater.notVeryEffectiveTypes.add(typeWater)
            typeWater.notVeryEffectiveTypes.add(typeGrass)
            typeWater.notVeryEffectiveTypes.add(typeDragon)
            typeWater.beSuperEffectiveTypes.add(typeGrass)
            typeWater.beSuperEffectiveTypes.add(typeElectric)
            typeWater.beNotVeryEffectiveTypes.add(typeSteel)
            typeWater.beNotVeryEffectiveTypes.add(typeFire)
            typeWater.beNotVeryEffectiveTypes.add(typeWater)
            typeWater.beNotVeryEffectiveTypes.add(typeIce)

            typeGrass.superEffectiveTypes.add(typeGround)
            typeGrass.superEffectiveTypes.add(typeRock)
            typeGrass.superEffectiveTypes.add(typeWater)
            typeGrass.notVeryEffectiveTypes.add(typeFlying)
            typeGrass.notVeryEffectiveTypes.add(typePoison)
            typeGrass.notVeryEffectiveTypes.add(typeBug)
            typeGrass.notVeryEffectiveTypes.add(typeSteel)
            typeGrass.notVeryEffectiveTypes.add(typeFire)
            typeGrass.notVeryEffectiveTypes.add(typeGrass)
            typeGrass.notVeryEffectiveTypes.add(typeDragon)
            typeGrass.beSuperEffectiveTypes.add(typeFlying)
            typeGrass.beSuperEffectiveTypes.add(typePoison)
            typeGrass.beSuperEffectiveTypes.add(typeBug)
            typeGrass.beSuperEffectiveTypes.add(typeFire)
            typeGrass.beSuperEffectiveTypes.add(typeIce)
            typeGrass.beNotVeryEffectiveTypes.add(typeGround)
            typeGrass.beNotVeryEffectiveTypes.add(typeWater)
            typeGrass.beNotVeryEffectiveTypes.add(typeGrass)
            typeGrass.beNotVeryEffectiveTypes.add(typeElectric)

            typeElectric.superEffectiveTypes.add(typeFlying)
            typeElectric.superEffectiveTypes.add(typeWater)
            typeElectric.notVeryEffectiveTypes.add(typeGrass)
            typeElectric.notVeryEffectiveTypes.add(typeElectric)
            typeElectric.notVeryEffectiveTypes.add(typeDragon)
            typeElectric.notEffectiveTypes.add(typeGround)
            typeElectric.beSuperEffectiveTypes.add(typeGround)
            typeElectric.beNotVeryEffectiveTypes.add(typeFlying)
            typeElectric.beNotVeryEffectiveTypes.add(typeSteel)
            typeElectric.beNotVeryEffectiveTypes.add(typeElectric)

            typePsychic.superEffectiveTypes.add(typeFighting)
            typePsychic.superEffectiveTypes.add(typePoison)
            typePsychic.notVeryEffectiveTypes.add(typeSteel)
            typePsychic.notVeryEffectiveTypes.add(typePsychic)
            typePsychic.notEffectiveTypes.add(typeDark)
            typePsychic.beSuperEffectiveTypes.add(typeBug)
            typePsychic.beSuperEffectiveTypes.add(typeGhost)
            typePsychic.beSuperEffectiveTypes.add(typeDark)
            typePsychic.beNotVeryEffectiveTypes.add(typeFighting)
            typePsychic.beNotVeryEffectiveTypes.add(typePsychic)

            typeIce.superEffectiveTypes.add(typeFlying)
            typeIce.superEffectiveTypes.add(typeGround)
            typeIce.superEffectiveTypes.add(typeGrass)
            typeIce.superEffectiveTypes.add(typeDragon)
            typeIce.notVeryEffectiveTypes.add(typeSteel)
            typeIce.notVeryEffectiveTypes.add(typeFire)
            typeIce.notVeryEffectiveTypes.add(typeWater)
            typeIce.notVeryEffectiveTypes.add(typeIce)
            typeIce.beSuperEffectiveTypes.add(typeFighting)
            typeIce.beSuperEffectiveTypes.add(typeRock)
            typeIce.beSuperEffectiveTypes.add(typeSteel)
            typeIce.beSuperEffectiveTypes.add(typeFire)
            typeIce.beNotVeryEffectiveTypes.add(typeIce)

            typeDragon.superEffectiveTypes.add(typeDragon)
            typeDragon.notVeryEffectiveTypes.add(typeSteel)
            typeDragon.notEffectiveTypes.add(typeFairy)
            typeDragon.beSuperEffectiveTypes.add(typeIce)
            typeDragon.beSuperEffectiveTypes.add(typeDragon)
            typeDragon.beSuperEffectiveTypes.add(typeFairy)
            typeDragon.beNotVeryEffectiveTypes.add(typeFire)
            typeDragon.beNotVeryEffectiveTypes.add(typeWater)
            typeDragon.beNotVeryEffectiveTypes.add(typeGrass)
            typeDragon.beNotVeryEffectiveTypes.add(typeElectric)

            typeDark.superEffectiveTypes.add(typeGhost)
            typeDark.superEffectiveTypes.add(typePsychic)
            typeDark.notVeryEffectiveTypes.add(typeFighting)
            typeDark.notVeryEffectiveTypes.add(typeDark)
            typeDark.notVeryEffectiveTypes.add(typeFairy)
            typeDark.beSuperEffectiveTypes.add(typeFighting)
            typeDark.beSuperEffectiveTypes.add(typeBug)
            typeDark.beSuperEffectiveTypes.add(typeFairy)
            typeDark.beNotVeryEffectiveTypes.add(typeGhost)
            typeDark.beNotVeryEffectiveTypes.add(typeDark)
            typeDark.beNotEffectiveTypes.add(typePsychic)

            typeFairy.superEffectiveTypes.add(typeFighting)
            typeFairy.superEffectiveTypes.add(typeDragon)
            typeFairy.superEffectiveTypes.add(typeDark)
            typeFairy.notVeryEffectiveTypes.add(typePoison)
            typeFairy.notVeryEffectiveTypes.add(typeSteel)
            typeFairy.notVeryEffectiveTypes.add(typeFire)
            typeFairy.beSuperEffectiveTypes.add(typePoison)
            typeFairy.beSuperEffectiveTypes.add(typeSteel)
            typeFairy.beNotVeryEffectiveTypes.add(typeFighting)
            typeFairy.beNotVeryEffectiveTypes.add(typeDark)
            typeFairy.beNotVeryEffectiveTypes.add(typeBug)
            typeFairy.beNotEffectiveTypes.add(typeDragon)
        }

        fun sort(types: ArrayList<Type>) = types.also { it.sortBy { t -> t.id } }

//        fun decorateAndSort(types: ArrayList<Type>): ArrayList<Type> {
//            val set = HashSet(types)
//            types.clear()
//            types.addAll(set)
//            this.sort(types)
//
//            return types
//        }

        fun getTypes() = arrayOf(
                typeNormal, typeFighting, typeFlying, typePoison, typeGround, typeRock,
                typeBug, typeGhost, typeSteel, typeFire, typeWater, typeGrass,
                typeElectric, typePsychic, typeIce, typeDragon, typeDark, typeFairy
        )
    }

    override fun toString() = this.name
}
