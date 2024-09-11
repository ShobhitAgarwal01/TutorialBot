package org.discord.tutorialBot.commads;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.discord.tutorialBot.ICommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Unmute implements ICommand {

    @Override
    public String getName() {
        return "unmute";
    }

    @Override
    public String getDescription() {
        return "Will UnMute the user";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.USER, "unmute", "The user to unmute", true));
        return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        Guild guild = event.getGuild();
        assert guild != null;
        Role role = guild.getRoleById(1282264792421240882L); // Role ID for the user who can mute/unmute
        assert member != null;

        if (member.getRoles().contains(role)) {
            Member unmutedMember = Objects.requireNonNull(event.getOption("unmute")).getAsMember();
            Role muteRole = guild.getRoleById(1283319903755042848L); // Mute role ID
            Role defaultRole = guild.getRoleById(1283327743827644487L); // Default role ID (that you want to reapply)
            assert unmutedMember != null;
            assert muteRole != null;
            assert defaultRole != null;

            // Remove the mute role
            guild.removeRoleFromMember(unmutedMember, muteRole).queue();
            // Add the default role back
            guild.addRoleToMember(unmutedMember, defaultRole).queue();

            // Restore permissions in text channels
            for (TextChannel textChannel : guild.getTextChannels()) {
                textChannel.upsertPermissionOverride(muteRole)
                        .clear(Permission.MESSAGE_SEND)
                        .queue(); // Clear the override for mute role
            }

            // Restore permissions in voice channels
            for (VoiceChannel voiceChannel : guild.getVoiceChannels()) {
                voiceChannel.upsertPermissionOverride(muteRole)
                        .clear(Permission.VOICE_SPEAK)
                        .queue(); // Clear the override for mute role
            }

            event.reply("Unmuted the member successfully").queue();
        } else {
            event.reply("You don't have permission to unmute users").queue();
        }
    }
}
