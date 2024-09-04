package org.discord.tutorialBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.discord.tutorialBot.commads.Buttons;
import org.discord.tutorialBot.commads.Embed;
import org.discord.tutorialBot.commads.Modals;
import org.discord.tutorialBot.commads.Sum;

public class MainApplication {
    public static void main(String[] args) {
        JDA jda = JDABuilder.createDefault(Token.TOKEN).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
        jda.addEventListener(new Listeners());
        CommandManager managers = new CommandManager();
        managers.add(new Sum());
        managers.add(new Embed());
        managers.add(new Buttons());
        managers.add(new Modals());
        jda.addEventListener(managers);
        /*
        *   Two  types of Slash Commands, they're:
        *   Global -- Used anywhere
        *   Guild -- Used in certain guilds
        */

    }
}
