package org.discord.tutorialBot.commads;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.discord.tutorialBot.ICommand;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.USER,"banned","This will ban the user", true));
        options.add(new OptionData(OptionType.INTEGER,"length","The length to ban", true));
        options.add(new OptionData(OptionType.STRING,"reason","The reason to ban",false));
        return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        if (member.hasPermission(Permission.BAN_MEMBERS)) {
            Member banned = event.getOption("banned").getAsMember();
            int length = event.getOption("length").getAsInt(); // Ban length in days
            OptionMapping reason = event.getOption("reason");

            if (banned != null) {
                if (reason == null) {
                    // Ban without a reason, specifying the time unit (e.g., days)
                    banned.ban(length, TimeUnit.DAYS).queue();
                    event.reply("Member banned for " + length + " days.").queue();
                } else {
                    // Ban with a reason
                    banned.ban(length, TimeUnit.DAYS).reason(reason.getAsString()).queue();
                    event.reply("Member banned for " + length + " days. Reason: " + reason.getAsString()).queue();
                }
            } else {
                event.reply("The specified member could not be found.").queue();
            }
        } else {
            event.reply("You do not have the required permission to execute this command.").queue();
        }
    }
}
