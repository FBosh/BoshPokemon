package com.boshpokemon.fragments

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.AdapterView
import com.boshpokemon.R
import com.boshpokemon.adapters.TypeAdapter
import com.boshpokemon.models.entities.Type
import kotlinx.android.synthetic.main.frag_type.*

class TypeFragment : BaseFragment() {
    companion object {
        private const val T_BSE_1 = "get Be Super Effective Types 2x"
        private const val T_BSE_2 = "get Be Super Effective Types 4x"
        private const val T_BNVE_1 = "get Be Not Very Effective Types 0.5x"
        private const val T_BNVE_2 = "get Be Not Very Effective Types 0.25x"
        private const val T_BNE = "get Be Not Effective Types 0x"
    }

    override val layoutRes = R.layout.frag_type

    private var selectedType1 = Type.typeUnknown
    private var selectedType2 = Type.typeUnknown

    private val mTypes = Type.getTypes().toList()

    override fun initUI() {
        tv_title?.also {
            it.text = getString(R.string.text_select_types)
        }

        initCheckBoxes()

        initSpinners()

        handleTVResult(true)

        initImageButton()

        initImageViews()
    }

    private fun initCheckBoxes() {
        cb_two_types?.also {
            it.text = getString(R.string.text_contain_two_types)
            it.setOnCheckedChangeListener { _, checked ->
                spin_type2?.also { spin ->
                    spin.isEnabled = checked
                    spin.adapter = TypeAdapter(
                            context ?: appCtx,
                            android.R.layout.simple_spinner_dropdown_item,
                            if (checked) mTypes.toMutableList().also { list -> list.remove(selectedType1) }
                            else arrayListOf(Type.typeUnknown)
                    )
                }

                ib_exchange.also { ib ->
                    ib.isEnabled = checked
                    ib.visibility = if (checked) View.VISIBLE else View.INVISIBLE
                }

                iv_spin2.visibility = if (checked) View.VISIBLE else View.GONE
            }
            it.isChecked = false
        }

        cb_keep_screen_on?.also {
            it.text = getString(R.string.text_keep_screen_on)
            it.setOnCheckedChangeListener { cb, checked -> cb.keepScreenOn = checked }
            it.isChecked = false
        }
    }

    private fun initSpinners() {
        arrayOf(spin_type1, spin_type2).forEach {
            val ctx = context ?: appCtx
            val spinnerLayoutRes = android.R.layout.simple_spinner_dropdown_item

            it.setBackgroundResource(R.color.color_transparent)
            it.setPopupBackgroundResource(R.color.color_transparent)

            when (it) {
                spin_type1 -> {
                    it.adapter = TypeAdapter(ctx, spinnerLayoutRes, mTypes)
                }

                spin_type2 -> {
                    it.adapter = TypeAdapter(ctx, spinnerLayoutRes, arrayOf(Type.typeUnknown))
                    it.isEnabled = false
                }
            }

            it.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    if (view == null || position < 0) return

                    when (parent.id) {
                        spin_type1.id -> {
                            selectedType1 = parent.adapter.getItem(position) as Type

                            if (spin_type2.isEnabled) {
                                spin_type2.also { spin ->
                                    val adapter = spin.adapter as TypeAdapter
                                    adapter.removeTemporarily(selectedType1)

                                    val index = adapter.getAL().indexOf(selectedType2)
                                    if (spin.selectedItemPosition != index) spin.setSelection(index)
                                }
                            }
                        }

                        spin_type2.id -> {
                            selectedType2 = parent.adapter.getItem(position) as Type

                            spin_type1.also { spin ->
                                val adapter = spin.adapter as TypeAdapter

                                if (!spin_type2.isEnabled) {
                                    adapter.recoverAL()
                                } else {
                                    adapter.removeTemporarily(selectedType2)

                                    val index = adapter.getAL().indexOf(selectedType1)
                                    if (spin.selectedItemPosition != index) spin.setSelection(index)
                                }
                            }
                        }
                    }

                    handleTVResult(false)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    //
                }
            }
        }
    }

    private fun handleTVResult(isInit: Boolean) {
        fun trimAL(resAL: ArrayList<Type>) =
                if (resAL.isNotEmpty()) resAL.toString().replace("[\\[\\]]".toRegex(), "")
                else getString(R.string.text_nothing)

        tv_result?.also {
            it.text =
                    if (!isInit && !cb_two_types.isChecked) {
                        """${getString(R.string.text_result)} : $selectedType1
                            |
                            |${getString(R.string.text_be_super_effective_1)} : ${trimAL(selectedType1.beSuperEffectiveTypes)}
                            |
                            |${getString(R.string.text_be_not_very_effective_1)} : ${trimAL(selectedType1.beNotVeryEffectiveTypes)}
                            |
                            |${getString(R.string.text_be_not_effective)} : ${trimAL(selectedType1.beNotEffectiveTypes)}
                        """.trimMargin()
                    } else if (!isInit && cb_two_types.isChecked) {
                        """${getString(R.string.text_result)} : $selectedType1, $selectedType2
                            |
                            |${getString(R.string.text_be_super_effective_1)} : ${trimAL(computeTypes(T_BSE_1, selectedType1, selectedType2))}
                            |
                            |${getString(R.string.text_be_super_effective_2)} : ${trimAL(computeTypes(T_BSE_2, selectedType1, selectedType2))}
                            |
                            |${getString(R.string.text_be_not_very_effective_1)} : ${trimAL(computeTypes(T_BNVE_1, selectedType1, selectedType2))}
                            |
                            |${getString(R.string.text_be_not_very_effective_2)} : ${trimAL(computeTypes(T_BNVE_2, selectedType1, selectedType2))}
                            |
                            |${getString(R.string.text_be_not_effective)} : ${trimAL(computeTypes(T_BNE, selectedType1, selectedType2))}
                        """.trimMargin()
                    } else {
                        "${getString(R.string.text_result)} : ${getString(R.string.text_nothing)}"
                    }

            if (isInit) it.setTextColor(getColor(R.color.color_black))

//            it.text = it.text.let { cs ->
//                var result = cs
//
//                for (type in mTypes) {
//                    result = result.replace(type.name.toRegex(), " ${type.name}")
//                }
//
//                result
//            }

            it.text = SpannableString(it.text).also { ss ->
                for (type in mTypes) {
                    if (!ss.contains(type.name)) continue

//                    val imageSpan = ImageSpan(
//                            ContextCompat.getDrawable(context!!, R.mipmap.ic_type_normal)!!.also { d ->
//                                d.setBounds(0, 0, 50, 50)
//                            }
//                    )

                    var handledIndex = 0

                    fun countTimes(): Int {
                        var str = ss.toString()
                        var result = 0

                        while (str.contains(type.name)) {
                            result++
                            str = str.replaceFirst(type.name.toRegex(), "")
                        }

                        return result
                    }

                    for (i in 0 until countTimes()) {
//                        ss.setSpan(
//                                imageSpan,
//                                ss.indexOf(type.name, handledIndex) - 1,
//                                ss.indexOf(type.name, handledIndex),
//                                Spannable.SPAN_INCLUSIVE_INCLUSIVE
//                        )

                        ss.setSpan(
                                ForegroundColorSpan(getColor(type.colorRes)),
                                ss.indexOf(type.name, handledIndex),
                                ss.indexOf(type.name, handledIndex) + type.name.length,
                                Spannable.SPAN_INCLUSIVE_INCLUSIVE
                        )

                        handledIndex = ss.indexOf(type.name, handledIndex) + type.name.length
                    }
                }
            }
        }
    }

    private fun computeTypes(what: String, type1: Type, type2: Type) =
            Type.sort(
                    arrayListOf<Type>().also {
                        val alIntersectionBSE = arrayListOf<Type>().also { list ->
                            for (t in type1.beSuperEffectiveTypes) {
                                if (type2.beSuperEffectiveTypes.contains(t)) list.add(t)
                            }
                        }

                        val alIntersectionBNVE = arrayListOf<Type>().also { list ->
                            for (t in type1.beNotVeryEffectiveTypes) {
                                if (type2.beNotVeryEffectiveTypes.contains(t)) list.add(t)
                            }
                        }

                        when (what) {
                            T_BSE_1 -> {
                                it.addAll(type1.beSuperEffectiveTypes + type2.beSuperEffectiveTypes)
                                it.removeAll(type1.beNotVeryEffectiveTypes + type2.beNotVeryEffectiveTypes
                                        + type1.beNotEffectiveTypes + type2.beNotEffectiveTypes + alIntersectionBSE
                                )
                            }

                            T_BSE_2 -> it.addAll(alIntersectionBSE)

                            T_BNVE_1 -> {
                                it.addAll(type1.beNotVeryEffectiveTypes + type2.beNotVeryEffectiveTypes)
                                it.removeAll(type1.beSuperEffectiveTypes + type2.beSuperEffectiveTypes
                                        + type1.beNotEffectiveTypes + type2.beNotEffectiveTypes + alIntersectionBNVE
                                )
                            }

                            T_BNVE_2 -> it.addAll(alIntersectionBNVE)

                            T_BNE -> {
                                it.addAll(type1.beNotEffectiveTypes + type2.beNotEffectiveTypes)
                            }
                        }
                    }
            )

    private fun initImageButton() {
        ib_exchange?.also {
            it.setOnClickListener {
                val temp = selectedType1
                selectedType1 = selectedType2
                selectedType2 = temp

                val tAdapter1 = spin_type1.adapter as TypeAdapter
                val tAdapter2 = spin_type2.adapter as TypeAdapter

                val al1 = tAdapter1.getAL()
                val al2 = tAdapter2.getAL()
                val tempAL = arrayListOf<Type>().also { al -> al.addAll(al1) }

                tAdapter1.initAL(al2)
                tAdapter2.initAL(tempAL)

                val position1 = tAdapter1.getPosition(selectedType1)
                val position2 = tAdapter2.getPosition(selectedType2)

                spin_type1.setSelection(position1)
                spin_type2.setSelection(position2)

                if (position1 != position2) return@setOnClickListener

                tAdapter1.notifyDataSetChanged()
                tAdapter2.notifyDataSetChanged()

                handleTVResult(false)
            }
            it.isEnabled = spin_type2.isEnabled
            it.visibility = if (spin_type2.isEnabled) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun initImageViews() {
        arrayOf(iv_spin1, iv_spin2).forEach {
            if (it == null) return@forEach

            it.scaleX = 0.75f
            it.scaleY = 0.75f
            it.setBackgroundResource(R.mipmap.ic_poke_ball)

            when (it) {
                iv_spin1 -> it.setOnClickListener { spin_type1.performClick() }

                iv_spin2 -> {
                    it.setOnClickListener { spin_type2.performClick() }
                    it.visibility = if (!spin_type2.isEnabled) View.GONE else View.VISIBLE
                }
            }
        }
    }
}
