package org.discord.tutorialBot.commads;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.discord.tutorialBot.ICommand;

import java.util.Collections;
import java.util.List;

public class Ban implements ICommand {
    @Override
    public String getName() {
        return "ban";
    }

    @Override
    public String getDescription() {
        return "Will Ban a Member";
    }

    @Override
    public List<OptionData> getOptions() {
        return Collections.emptyList();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        if(member.hasPermission(Permission.BAN_MEMBERS)) {
            event.reply("Banning Player").queue();
        } else {
            event.reply("You do not have the required Permission to execute this command").queue();
        }
    }
}
