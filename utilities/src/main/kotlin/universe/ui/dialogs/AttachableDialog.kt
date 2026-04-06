package universe.ui.dialogs

import arc.Core
import arc.math.Interp
import arc.scene.Element
import arc.scene.style.Drawable
import arc.scene.ui.layout.Table
import arc.util.Align
import mindustry.gen.Icon
import mindustry.ui.Styles
import universe.ui.element.UCollapser
import universe.ui.enterSt
import universe.ui.exitSt

open class AttachableDialog(
  val attachedDialog: arc.scene.ui.Dialog,
  switchIcon: Drawable,
  switchDesc: String,
  title: String,
  style: DialogStyle = Styles.defaultDialog,
  replace: Boolean = true,
): mindustry.ui.dialogs.BaseDialog(title, style) {
  companion object {
    private val attachedMap = mutableMapOf<arc.scene.ui.Dialog, MutableList<AttacheEntry>>()
    private var fallback = false

    fun showFirstAttache(attachedDialog: arc.scene.ui.Dialog): arc.scene.ui.Dialog? =
      attachedMap[attachedDialog]
        ?.map { it.attachableDialog }
        ?.firstOrNull { it.enabled }
        ?.show()

    fun getAttacheDialogs(attachedDialog: arc.scene.ui.Dialog) =
      attachedMap
        .getOrElse(attachedDialog) { mutableListOf() }
        .toList()
  }

  var enabled = true

  init {
    attachedMap.getOrPut(attachedDialog) { mutableListOf() }.add(AttacheEntry(
      this,
      switchIcon,
      switchDesc
    ))
    attachedDialog.shown {
      if (fallback) return@shown
      Core.app.post {
        val attach = showFirstAttache(attachedDialog)
        if (attach != null && replace) {
          attachedDialog.clearActions()
          attachedDialog.hide(null)
        }
      }
    }
  }

  fun getSwitchableEntries() = attachedMap[attachedDialog]?.toList()?: emptyList()

  fun Element.setupSwitchListener(entry: AttacheEntry) {
    clicked {
      hide(null)
      entry.attachableDialog.show(Core.scene)
    }
  }

  fun Element.setupFallbackListener(entry: AttacheEntry) {
    clicked {
      hide(null)
      fallback = true
      attachedDialog.show(Core.scene)
      fallback = false
    }
  }

  fun buildDefaultLeftSwitchTable(): Table {
    val entries = getSwitchableEntries()

    var hovering = false
    val tab = Table()
    tab.left().defaults().left().growX().fillY()

    entries.forEach { entry ->
      tab.addEntryButton(
        entry,
        { hovering },
        { hovering = it }
      ){
        hide(null)
        entry.attachableDialog.show(Core.scene)
      }
      tab.row()
    }

    tab.addEntryButton(
      null,
      { hovering },
      { hovering = it }
    ){
      hide(null)
      fallback = true
      attachedDialog.show(Core.scene)
      fallback = false
    }

    return tab
  }

  private fun Table.addEntryButton(
    entry: AttacheEntry?,
    hovering: () -> Boolean,
    setHovering: (Boolean) -> Unit,
    call: () -> Unit
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

  data class AttacheEntry(
    val attachableDialog: AttachableDialog,
    val switchIcon: Drawable,
    val switchDesc: String,
  ){
    val isShown get() = attachableDialog.isShown
  }
}