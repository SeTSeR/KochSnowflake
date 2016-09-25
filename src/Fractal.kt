/**
 * Created by setser on 22.09.16.
 */
class Fractal {

    var fract: MutableList<Pair<Int, Double>>

    val GO: Int = 0
    val TURN: Int = 1

    constructor() {
        this.fract = mutableListOf()
    }

    constructor(startStep: Double) {
        this.fract = mutableListOf(Pair(GO, startStep))
    }

    fun next(): Fractal {
        val fractal = Fractal()
        var i = 0
        fun translate(input: Pair<Int, Double>) : Pair<Int, Double> {
            if(input.first == GO)
                return Pair(input.first, input.second / 3)
            else
                return input
        }
        val fractlist = fract.map(::translate)
        fractal.fract.clear()
        fractal.fract.addAll(i, fractlist)
        i += fract.size
        fractal.fract.add(i, Pair(TURN, -Math.PI/3))
        ++i
        fractal.fract.addAll(i, fractlist)
        i += fract.size
        fractal.fract.add(i, Pair(TURN, Math.PI*2/3))
        ++i
        fractal.fract.addAll(i, fractlist)
        i += fract.size
        fractal.fract.add(i, Pair(TURN, -Math.PI/3))
        ++i
        fractal.fract.addAll(i, fractlist)
        i += fract.size
        return fractal
    }

    fun toDraw(): Fractal {
        val result = Fractal()
        var i = 0
        result.fract.addAll(i, fract)
        i += fract.size
        result.fract.add(i, Pair(TURN, Math.PI*2/3))
        ++i
        result.fract.addAll(i, fract)
        i += fract.size
        result.fract.add(i, Pair(TURN, Math.PI*2/3))
        ++i
        result.fract.addAll(i, fract)
        i += fract.size
        return result
    }
}