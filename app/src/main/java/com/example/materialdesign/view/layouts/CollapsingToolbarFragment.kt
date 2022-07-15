package com.example.materialdesign.view.layouts

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BulletSpan
import android.text.style.TypefaceSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.provider.FontRequest
import androidx.core.provider.FontsContractCompat
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentCollapsingToolbarBinding

class CollapsingToolbarFragment : Fragment() {
    private var _binding: FragmentCollapsingToolbarBinding? = null
    private val binding: FragmentCollapsingToolbarBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollapsingToolbarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMyText()
    }

    @SuppressLint("NewApi")
    private fun setMyText() {
        val request = FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "Sofia",
            R.array.com_google_android_gms_fonts_certs
        )
        val text =
            "Пи́во — слабоалкогольный напиток[1], получаемый спиртовым брожением солодового сусла (чаще всего на основе ячменя) с помощью пивных дрожжей, обычно с добавлением хмеля.\n" +
                    "        Содержание этилового спирта (крепость) в большинстве сортов пива около 3,0—6,0 % об. (крепкое содержит, как правило, от 8,5 % до 14 % об., иногда также выделяют лёгкое пиво, которое содержит 1—2 % об., отдельно выделяют безалкогольное пиво, которое сюда не включают), сухих веществ (в основном углеводов) 7—10 %, углекислого газа 0,48—1,0 %[2]. В пиве содержится более 800 соединений, которые определяют его вкус и аромат[3].\n" +
                    "        Пиво распространено во множестве стран мира и пользуется популярностью благодаря своим вкусовым качествам и аромату. Существует около тысячи сортов пива. Вкусовые характеристики разных видов могут кардинально различаться.\n" +
                    "        Пиво — один из древнейших напитков, известный с эпохи неолита вместе с мёдом, квасом и вином. Существует мнение, что само выращивание зерновых началось именно ради пива, а не хлеба[12][13]. Так анализ органических следов в каменных ступках, найденных в израильской пещере Ракефет[en], показал, что натуфийцы производили пиво в 11-м тысячелетии до н. э. из пшеницы и ячменя ещё до того, как начали зерновые культуры использовать для выпечки хлеба[14]. Древнее пиво было похоже скорее на кашу, чем на пенный напиток[15]. Эндосперм ячменя, используемый для пивоварения, идентифицирован на фрагментах керамики (черепках)[en] из Западного Кургана Чатал-Хююка, датированных 5900—5800 гг. до н. э. (калиброванная дата)[16].\n" +
                    "        Свидетельства о варке пива из района древнего Ирана и шумерской культуры Древней Месопотамии датируются периодом около 3500—2900 годов до н. э. Находки свидетельствуют также, что примерно в это время пивоварением занимались и египтяне, а позже вавилоняне. От египтян пивоварение переняли евреи.\n" +
                    "        Пиво упоминается в древнеегипетских и месопотамских источниках, начиная с примерно 3000 до н. э. Строители египетских пирамид получали, помимо пищевого довольствия, пиво.\n" +
                    "        Пиво имело распространение во всём древнем мире, в частности в древней Греции и древнем Риме (хотя и уступало в популярности вину), древней Армении, его знали кельты и германские народы. Пиво варили из пшеницы, овса, ржи, проса, ячменя и полбы. В древнем Китае варилось пиво из проросшего риса, а также из риса и фруктов.\n" +
                    "        Пиво, как правило, изготавливали в северных регионах, где климатические условия не позволяли выращивать виноград.\n" +
                    "        В Раннее Средневековье пиво в Европе производилось преимущественно в монастырях. Европейские монахи профессионально занимались пивоварением, усовершенствовали его технологию; в частности, начав использовать в качестве консерванта хмель.\n" +
                    "        В средние века пиво считалось напитком как для взрослых, так и для детей — в отличие от тогдашней питьевой воды, пиво, в результате кипячения, было лишено возбудителей болезней. Также благодаря большому содержанию калорий, пиво было дополнительным продуктом питания; в Германии и сегодня пиво называют жидким хлебом (нем. flüssiges Brot). В те времена пиво считалось напитком бедняков, имело более низкий статус по сравнению с вином.\n" +
                    "        Существует мнение, что восточные славяне в VII—IX веках варили пиво из проса и ржи, однако затем в Х-XII веках пиво всё чаще начинают изготавливать из ячменного солода и муки. В Новгородских берестяных грамотах XIV века упоминается ячменное пиво. Впервые упоминание производства пива зафиксировано в новгородской берестяной грамоте № 3: «Поклон от Грикши к Есифу. Прислав Онанья, молви… Яз ему отвечал: на рекл ми Есиф варити перевары ни на кого. Он прислал к Федосьи: вари ты пив, седишь на безатьщине, не варишь жито…»[17][18]. Для праздничных поминальных обрядов «женили пиво» — добавляли в пиво мёд (ср. «мёд-пиво» русских сказок[источник не указан 798 дней]). Во времена Ивана III было введено исключительное право казны на варку пива на продажу, и пиво вместе с хлебным вином стали продавать в царёвых кабаках. В XVII веке крестьянам разрешалось самим варить пиво четыре раза, в основные церковные праздники, позже это стало разрешено в дополнительные праздники."
        var spannableStringBuilder = SpannableStringBuilder(text)

//        spannableStringBuilder.setSpan(
//            TypefaceSpan(requireActivity().resources.getFont(R.font.sofia)),
//            0,
//            spannableStringBuilder.length,
//            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//        )

        binding.textCollapsingToolbar.setText(
            spannableStringBuilder, TextView.BufferType.EDITABLE
        )
        spannableStringBuilder = binding.textCollapsingToolbar.text as SpannableStringBuilder
        val callback = object : FontsContractCompat.FontRequestCallback() {
            @SuppressLint("NewApi")
            override fun onTypefaceRetrieved(typeface: Typeface?) {
                typeface?.let {
                    spannableStringBuilder.setSpan(
                        TypefaceSpan(it),
                        0,
                        spannableStringBuilder.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
        }
        FontsContractCompat.requestFont(
            requireContext(),
            request,
            callback,
            Handler(Looper.getMainLooper())
        )
        val list = text.indexesOf("\n")

        var current = 0
        list.forEach {
            spannableStringBuilder.setSpan(
                BulletSpan(
                    20,
                    ContextCompat.getColor(requireContext(), R.color.colorAccent),
                    20
                ), current, it, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            current = it + 1
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CollapsingToolbarFragment()
    }

    fun String.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> =
        (if (ignoreCase) Regex(substr, RegexOption.IGNORE_CASE) else Regex(substr))
            .findAll(this).map { it.range.first }.toList()
}