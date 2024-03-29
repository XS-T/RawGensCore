package net.xst.RawGensPlugin.commands.Warn

import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandDescription
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.CommandPermission
import com.google.gson.reflect.TypeToken
import com.google.inject.Inject
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.luckperms.api.node.types.PermissionNode
import net.xst.RawGensPlugin.RawGensCorePlugin
import net.xst.RawGensPlugin.RawGensCorePlugin.Companion.api
import net.xst.RawGensPlugin.RawGensCorePlugin.Companion.warnsmanager
import net.xst.RawGensPlugin.Utils.WarnPlayer
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.io.File
import com.google.gson.GsonBuilder
import net.kyori.adventure.text.Component.text
import net.xst.RawGensPlugin.RawGensCorePlugin.Companion.pluginmsg
import net.xst.RawGensPlugin.RawGensCorePlugin.Companion.warnsDir

class unwarn_command @Inject constructor(private val plugin: RawGensCorePlugin) {
	@CommandMethod("unwarn <target> <warnnum> <reason>")
	@CommandDescription("Remove a player's warning")
	@CommandPermission("rawgens.command.unwarn")
	suspend fun unwarn(
		player: Player,
		@Argument("target") target: Player,
		@Argument("warnnum") warnIndex: Int,
		@Argument("reason") reason: Array<String>
	) {
		val reason_message = reason.joinToString(" ")
		val warnings = warnsmanager.getWarnings(player.uniqueId)
		/*if (warnIndex < 1) {
			player.sendMessage(text("Invalid index0. Size:${warnings.size} IndexNum:$warnIndex", NamedTextColor.RED))
			return
		}*/

		if (warnIndex > warnings.size) {
			player.sendMessage("$pluginmsg Invalid index")
			return
		}
		player.sendMessage("$pluginmsg Sucessfully removed warning #$warnIndex from ${target.displayName} reasoning $reason_message")
			warnsmanager.removeWarning(player.uniqueId, warnIndex)
		}
	}