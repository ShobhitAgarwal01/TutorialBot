package org.discord.tutorialBot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Listeners extends ListenerAdapter {

//    @Override
//    public void onReady(@NotNull ReadyEvent event){
//        JDA jda = event.getJDA();
//        for(Guild guild : jda.getGuilds()){
//            for(TextChannel channel : guild.getTextChannels()){
//                channel.sendMessage("Hola Amigos").queue();
//            }
//        }
//        Guild guild = event.getJDA().getGuildById(1279777520396402740L);
////        assert guild != null;
//        event.getJDA().upsertCommand("sum","Gives the Sum of two numbers").addOptions(
//                new OptionData(OptionType.INTEGER,"number1","First Number",true)
//                        .setMinValue(1)
//                        .setMaxValue(100),
//                new OptionData(OptionType.INTEGER,"number2","The Second Number",false)
//                        .setMinValue(10)
//                        .setMaxValue(1000)
//        ).queue();
//    }

//    @Override
//    public void onMessageReceived(@NotNull MessageReceivedEvent event){
//        if(event.getAuthor().isBot()) return;
//        MessageChannel channel = event.getChannel();
//        channel.sendMessage(event.getMessage().getContentRaw()).queue();
//    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event){
        if(Objects.equals(event.getButton().getId(), "yes-button")){
            event.reply("Ohh! Nice I do like it as well").queue();
        } else if(Objects.equals(event.getButton().getId(), "no-button")){
            event.reply("What Monster? You don't like Pizza?").queue();
        }

        event.getMessage().delete().queue();
    }

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event){
        if(event.getModalId().equals("person-modal")){
            ModalMapping nameValue = event.getValue("name-field");
            ModalMapping ageValue = event.getValue("age-field");
            ModalMapping descriptionValue = event.getValue("description-field");

            String name = nameValue.getAsString();
            String description = descriptionValue.getAsString();

            String age;
            if(ageValue.getAsString().isBlank()){
                age = "N/A";
            } else {
                age = ageValue.getAsString();
            }

            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle(name);
            builder.setDescription("The Description of "+ name);
            builder.addField("Name",name,false);
            builder.addField("Age",age,false);
            builder.addField("Description",description,false);
            event.replyEmbeds(builder.build()).queue();
        }
    }
}
