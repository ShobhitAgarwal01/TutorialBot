package org.discord.tutorialBot.commads;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.discord.tutorialBot.ICommand;
import org.jetbrains.annotations.NotNull;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;

//public class Sum extends ListenerAdapter {
public class Sum implements ICommand {

    @Override
    public String getName() {
        return "sum";
    }

    @Override
    public String getDescription() {
        return "Will Take the Sum of Two Numbers";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.INTEGER,"number1","The First Number",true)
                .setMinValue(1)
                .setMaxValue(100));
        data.add(new OptionData(OptionType.INTEGER,"number2","The Second Number",false)
                .setMinValue(10)
                .setMaxValue(100));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
//        if(!event.getName().equals("sum")) return;
//        event.reply("Hello World").queue();
        OptionMapping number1 = event.getOption("number1");
        int num1 = number1.getAsInt();
        OptionMapping number2 = event.getOption("number2");
        int num2 = 1;
        if (number2 != null) {
            num2 = number2.getAsInt();
        }
        int result = num1 + num2;
        event.reply(result + " ").queue();
    }
}
