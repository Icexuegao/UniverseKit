package universe.graphic

import arc.Core
import arc.Events
import arc.graphics.Color
import arc.graphics.GL30
import arc.graphics.Gl
import arc.graphics.g2d.Draw
import arc.graphics.gl.FrameBuffer
import arc.graphics.gl.GLFrameBuffer
import arc.graphics.gl.Shader
import mindustry.game.EventType

object ScreenSampler {
  private val currentBoundBuffer = GLFrameBuffer::class.java.getDeclaredField("currentBoundFramebuffer")
    .also { it.isAccessible = true }
  private val swapBuffer: FrameBuffer = FrameBuffer()
  private val baseScreen: Shader = Shader(
    """
    attribute vec4 a_position;
    attribute vec2 a_texCoord0;
    
    varying vec2 v_texCoords;
    
    void main(){
        v_texCoords = a_texCoord0;
        gl_Position = a_position;
    }
    """.trimIndent(),
    """
    uniform sampler2D u_texture;

    varying vec2 v_texCoords;

    void main() {
        gl_FragColor.rgb = texture2D(u_texture, v_texCoords).rgb;
        gl_FragColor.a = 1.0;
    }
    """.trimIndent()
  )

  init {
    swapBuffer.resize(Core.graphics.width, Core.graphics.height)
    Events.on(EventType.ResizeEvent::class.java) { event ->
      swapBuffer.resize(Core.graphics.width, Core.graphics.height)
    }
  }

  @JvmStatic
  fun toBuffer(target: FrameBuffer) {
    val buffer = currentBoundBuffer.get(null) as? GLFrameBuffer<*>

    return buffer?.run {
      if (buffer.width == target.width && buffer.height == target.height) {
        buffer.begin()
        target.texture.bind()
        Gl.copyTexSubImage2D(
          Gl.texture2d,
          0,
          0, 0,
          0, 0,
          target.width, target.height,
        )
        Gl.bindTexture(Gl.texture2d, 0)
        buffer.end()
      }
      else {
        blitBuffer(buffer, target)
      }
    }?: run {
      if (swapBuffer.width == target.width && swapBuffer.height == target.height) {
        Draw.flush()
        target.texture.bind()
        Gl.copyTexSubImage2D(
          Gl.texture2d,
          0,
          0, 0,
          0, 0,
          target.width, target.height,
        )
        Gl.bindTexture(Gl.texture2d, 0)
      }
      else {
        Draw.flush()
        swapBuffer.texture.bind()
        Gl.copyTexSubImage2D(
          Gl.texture2d,
          0,
          0, 0,
          0, 0,
          swapBuffer.width, swapBuffer.height,
        )
        Gl.bindTexture(Gl.texture2d, 0)

        blitBuffer(swapBuffer, target)
      }
    }
  }

  private fun blitBuffer(source: GLFrameBuffer<*>, target: GLFrameBuffer<*>) {
    Core.gl30?.run {
      glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, source.framebufferHandle)
      glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, target.framebufferHandle)
      glBlitFramebuffer(
        0, 0, source.width, source.height,
        0, 0, target.width, target.height,
        Gl.colorBufferBit, Gl.nearest
      )
      glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, 0)
      glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, 0)
    } ?: Core.gl20.run {
      target.begin(Color.clear)
      source.texture.bind(0)
      Draw.blit(baseScreen)
      Gl.bindTexture(Gl.texture2d, 0)
      target.end()
    }
  }
}