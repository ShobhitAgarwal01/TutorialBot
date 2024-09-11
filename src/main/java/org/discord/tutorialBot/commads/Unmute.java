package org.discord.tutorialBot.commads;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.discord.tutorialBot.ICommand;

import java.util.Collections;
import java.util.List;

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
        return Collections.emptyList();
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {

    }
}
