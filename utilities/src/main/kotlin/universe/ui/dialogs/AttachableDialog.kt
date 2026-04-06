package universe.ui.dialogs

import arc.Core
import arc.func.Boolp
import arc.math.Interp
import arc.scene.Element
import arc.scene.actions.Actions
import arc.scene.style.Drawable
import arc.scene.ui.Dialog
import arc.scene.ui.ImageButton
import arc.scene.ui.layout.Table
import mindustry.gen.Icon
import mindustry.ui.Styles
import mindustry.ui.dialogs.BaseDialog
import universe.shared.SharedObject
import universe.ui.element.UCollapser
import universe.ui.enterSt
import universe.ui.exitSt
import java.lang.reflect.Field
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.reflect.jvm.javaField

open class AttachableDialog(
  val attachedDialog: Dialog,
  switchIcon: Drawable,
  switchDesc: String,
  title: String,
  style: DialogStyle = Styles.defaultDialog,
  replace: Boolean = true,
): BaseDialog(title, style) {
  companion object: SharedObject() {
    private var attachedMap = mutableMapOf<Dialog, MutableList<Dialog>>()
    private var attachedEnabledMap = mutableMapOf<Dialog, MutableMap<Dialog, Boolp>>()
    private var attachedIconMap = mutableMapOf<Dialog, MutableMap<Dialog, Drawable>>()
    private var attachedDescMap = mutableMapOf<Dialog, MutableMap<Dialog, String>>()
    private var fallback = AtomicBoolean(false)

    init {
      setupSharedReferences()
    }

    override fun sharedReferenceFields(): List<Field> = listOf(
      ::attachedMap.javaField!!,
      ::attachedEnabledMap.javaField!!,
      ::attachedIconMap.javaField!!,
      ::attachedDescMap.javaField!!,
      ::fallback.javaField!!,
    )

    fun showFirstAttache(attachedDialog: Dialog): Dialog? =
      attachedMap[attachedDialog]
        ?.firstOrNull { attachedEnabledMap[attachedDialog]!![it]!!.get() }
        ?.show()

    fun getAttacheDialogs(attachedDialog: Dialog) =
      attachedMap
        .getOrElse(attachedDialog) { emptyList() }
        .map {
          AttacheEntry(
            it,
            attachedIconMap[attachedDialog]!![it]!!,
            attachedDescMap[attachedDialog]!![it]!!,
          )
        }

    override fun sharedID(): String = "attachableDialog@a17fc3bd4"
  }

  var enabled = true

  init {
    attachedMap.getOrPut(attachedDialog) {
      attachedDialog.shown {
        if (fallback.get()) return@shown
        Core.app.post {
          val attach = showFirstAttache(attachedDialog)
          if (attach != null && replace) {
            attachedDialog.clearActions()
            attachedDialog.hide(null)
          }
        }
      }

      mutableListOf()
    }.add(this)
    attachedEnabledMap.getOrPut(attachedDialog) { mutableMapOf() }[this] = Boolp{ enabled }
    attachedIconMap.getOrPut(attachedDialog) { mutableMapOf() }[this] = switchIcon
    attachedDescMap.getOrPut(attachedDialog) { mutableMapOf() }[this] = switchDesc
  }

  fun Table.fillDefaultSwitch(){
    fill {
      if (Core.graphics.isPortrait) {
        val coll = UCollapser(
          collX = false,
          collY = true,
          collapsed = true
        ){ t ->
          t.table(Styles.grayPanel) { b ->
            b.add(buildDefaultBottomSwitchTable()).fillY().growX().pad(6f)
          }.growX().fillY()
        }.setDuration(0.3f, Interp.pow3Out)
        val foldButton = ImageButton(Icon.upOpen, Styles.flati)
        foldButton.clicked {
          coll.setCollapsed(!coll.collapse)
          if (coll.collapse) {
            foldButton.image.clearActions()
            foldButton.image.addAction(
              Actions.rotateTo(0f, 0.3f)
            )
          }
          else {
            foldButton.image.clearActions()
            foldButton.image.addAction(
              Actions.rotateTo(180f, 0.3f)
            )
          }
        }

        it.bottom().add(foldButton).growX().height(32f)
        it.row()
        it.add(coll).growX().fillY()
      }
      else {
        it.top().left().add(
          buildDefaultLeftSwitchTable()
        )
      }
    }
  }

  fun getSwitchableEntries() = attachedMap[attachedDialog]
    ?.map{ dialog ->
      AttacheEntry(
        dialog,
        attachedIconMap[attachedDialog]!![dialog]!!,
        attachedDescMap[attachedDialog]!![dialog]!!,
      )
    }?: emptyList()

  fun Element.setupSwitchListener(entry: AttacheEntry) {
    clicked {
      hide(null)
      entry.attachableDialog.show(Core.scene)
    }
  }

  fun Element.setupFallbackListener(entry: AttacheEntry) {
    clicked {
      hide(null)
      fallback.set(true)
      attachedDialog.show(Core.scene)
      fallback.set(false)
    }
  }

  fun buildDefaultLeftSwitchTable(): Table {
    val entries = getSwitchableEntries()

    var hovering = false
    val tab = Table()
    tab.left().defaults().left().growX().fillY()

    entries.forEach { entry ->
      tab.addSideButton(
        entry,
        { hovering },
        { hovering = it }
      ){
        hide(null)
        entry.attachableDialog.show(Core.scene)
      }
      tab.row()
    }

    tab.addSideButton(
      null,
      { hovering },
      { hovering = it }
    ){
      hide(null)
      fallback.set(true)
      attachedDialog.show(Core.scene)
      fallback.set(false)
    }

    return tab
  }

  fun buildDefaultBottomSwitchTable(): Table {
    val entries = getSwitchableEntries()

    val tab = Table()
    tab.left().defaults().left().growX().fillY()

    entries.forEach { entry ->
      tab.addListButton(entry){
        hide(null)
        entry.attachableDialog.show(Core.scene)
      }
      tab.row()
    }

    tab.addListButton(null){
      hide(null)
      fallback.set(true)
      attachedDialog.show(Core.scene)
      fallback.set(false)
    }

    return tab
  }

  private fun Table.addSideButton(
    entry: AttacheEntry?,
    hovering: () -> Boolean,
    setHovering: (Boolean) -> Unit,
    call: () -> Unit,
  ) {
    button({
      it.left().defaults().left()
      it.image(entry?.switchIcon?: Icon.left).size(46f).pad(4f)
      it.add(UCollapser(
        collX = true,
        collY = false,
        collapsed = true,
      ){ t ->
        t.add(entry?.switchDesc?: Core.bundle["back"]).pad(4f).padRight(8f)
      }.setCollapsed { !hovering() }.setDuration(0.3f, Interp.pow3Out))
    }, Styles.cleart) { call() }.get().also {
      it.enterSt { setHovering(true) }
      it.exitSt { setHovering(false) }
    }
  }

  private fun Table.addListButton(
    entry: AttacheEntry?,
    call: () -> Unit,
  ) {
    button({
      it.left().defaults().left()
      it.image(entry?.switchIcon?: Icon.left).size(46f).pad(4f)
      it.add(entry?.switchDesc?: Core.bundle["back"]).pad(4f).padRight(8f)
    }, Styles.cleart) { call() }
  }

  data class AttacheEntry(
    val attachableDialog: Dialog,
    val switchIcon: Drawable,
    val switchDesc: String,
  ){
    val isShown get() = attachableDialog.isShown
  }
}