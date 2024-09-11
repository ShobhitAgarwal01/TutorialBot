package org.discord.tutorialBot.commads;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.discord.tutorialBot.ICommand;

import java.util.Collections;
import java.util.List;

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
        return Collections.emptyList();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        Guild guild = event.getGuild();
        Role role = guild.getRoleById(1282264792421240882L);
        if(member.getRoles().contains(role)){
            event.reply("Muting User").queue();
        } else {
            event.reply("You don't have Permission to mute user").queue();
        }
    }
}
