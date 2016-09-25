/**
 * Created by setser on 22.09.16.
 */

import javafx.application.Application;
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color

class Main : Application() {

    val width = 675.0
    val height = 500.0
    val canvas = Canvas(width, height)

    var fractal = Fractal(400.0)

    private fun draw(fractal: Fractal) {
        val context = canvas.graphicsContext2D
        var x = 200.0
        var y = height - 100/Math.sqrt(3.0)
        var angle = -Math.PI/2
        context.fill = Color.BLACK
        context.fillRect(0.0, 0.0, width, height)
        context.stroke = Color.GREEN
        context.moveTo(x, y)
        for (action in fractal.fract) {
            when(action.first) {
                fractal.GO -> {
                    x += action.second * Math.cos(angle)
                    y += action.second * Math.sin(angle)
                    context.lineTo(x, y)
                }
                fractal.TURN -> angle += action.second
            }
        }
        context.closePath()
        context.stroke()
    }

    override fun start(primaryStage: Stage?) {
        System.setProperty("prism.lcdtext", "false")
        val stackPane = StackPane()
        draw(fractal.toDraw())
        canvas.onKeyReleased = EventHandler<KeyEvent> { keyEvent ->
            if (keyEvent.code == KeyCode.SPACE) {
                fractal = fractal.next()
                draw(fractal.toDraw())
            }
        }
        canvas.isFocusTraversable = true
        stackPane.children.add(canvas)
        primaryStage?.scene = Scene(stackPane)
        primaryStage?.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}