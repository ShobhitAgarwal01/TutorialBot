package org.discord.tutorialBot.commads;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.internal.interactions.modal.ModalImpl;
import org.discord.tutorialBot.ICommand;
import org.w3c.dom.Text;

import java.util.List;

public class Modals implements ICommand {
    @Override
    public String getName() {
        return "modal";
    }

    @Override
    public String getDescription() {
        return "will open a modal";
    }

    @Override
    public List<OptionData> getOptions() {
        return null;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        TextInput name = TextInput.create("name-field","Name", TextInputStyle.SHORT)
                .setRequired(true)
                .setMinLength(1)
                .setMaxLength(100)
                .build();

        TextInput age = TextInput.create("age-field","Age",TextInputStyle.SHORT)
                .setRequired(false)
                .build();

        TextInput description = TextInput.create("description-field","Description",TextInputStyle.PARAGRAPH)
                .setRequired(true)
                .setPlaceholder("Describe Yourself")
                .setMinLength(1)
                .build();

        Modal modal = Modal.create("person-modal","Describe Yourself")
                .addActionRow(name)
                .addActionRow(age)
                .addActionRow(description)
                .build();

        event.replyModal(modal).queue();
    }
}
