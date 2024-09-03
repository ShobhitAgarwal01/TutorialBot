package org.discord.tutorialBot.commads;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessagePollBuilder;
import org.discord.tutorialBot.ICommand;

import java.util.Collection;
import java.util.List;

public class Buttons implements ICommand {
    @Override
    public String getName() {
        return "button";
    }

    @Override
    public String getDescription() {
        return "Buttons";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Pizza?");
        embedBuilder.setDescription("Do you Like Pizza?");

        Button yesButton = Button.danger("yes-button","Yes");
        Button noButton = Button.danger("no-button","No");

//        Message message = new MessageBuilder()
//                .setEmbeds(embedBuilder.build())
//                .setActionRows(ActionRow.partitionOf(yesButton,noButton))
//                .build();

//        event.reply(message).queue();
        event.replyEmbeds(embedBuilder.build())
                .addActionRow(yesButton, noButton)
                .queue();
    }
}
