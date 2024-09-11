package org.discord.tutorialBot.commads;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.discord.tutorialBot.ICommand;

import java.util.Collections;
import java.util.List;

public class Staff implements ICommand {
    @Override
    public String getName() {
        return "staff";
    }

    @Override
    public String getDescription() {
        return "Will give you Staff Role";
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
        guild.addRoleToMember(member, role).queue();
        event.reply("Role Added").queue();
    }
}
