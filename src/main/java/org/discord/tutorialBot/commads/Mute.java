package org.discord.tutorialBot.commads;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.discord.tutorialBot.ICommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Mute implements ICommand {
    @Override
    public String getName() {
        return "mute";
    }

    @Override
    public String getDescription() {
        return "will mute a memeber";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.USER, "muted", "The user to mute", true));
        return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        Guild guild = event.getGuild();
        assert guild != null;
        Role role = guild.getRoleById(1282264792421240882L);
        assert member != null;
        if(member.getRoles().contains(role)){
            Member mutedMember = Objects.requireNonNull(event.getOption("muted")).getAsMember();
            Role muteRole = guild.getRoleById(1283319903755042848L);
            Role defaultRole = guild.getRoleById(1283327743827644487L);
            assert mutedMember != null;
            assert defaultRole != null;
            guild.removeRoleFromMember(mutedMember, defaultRole).queue();
            assert muteRole != null;
            guild.addRoleToMember(mutedMember, muteRole).queue();
            // Ensure the user is muted in all text and voice channels
            for (TextChannel textChannel : guild.getTextChannels()) {
                textChannel.upsertPermissionOverride(muteRole)
                        .deny(Permission.MESSAGE_SEND)
                        .queue();
            }

            for (VoiceChannel voiceChannel : guild.getVoiceChannels()) {
                voiceChannel.upsertPermissionOverride(muteRole)
                        .deny(Permission.VOICE_SPEAK)
                        .queue();
            }

            event.reply("Muted the member successfully").queue();
        } else {
            event.reply("You don't have Permission to mute user").queue();
        }
    }
}
