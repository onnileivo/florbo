package dev.florbo.hud;

import cc.polyfrost.oneconfig.config.annotations.Dropdown;
import cc.polyfrost.oneconfig.hud.SingleTextHud;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.commons.lang3.time.DurationFormatUtils;

public class TimerHud extends SingleTextHud {
    public long tickCounter;
    public boolean paused;

    public TimerHud() {
        super("", true);
        MinecraftForge.EVENT_BUS.register(this);
        reset();
    }
    public String[] options = {"H:mm:ss.SSS", "HH:mm:ss.SSS", "H:mm", "H:mm:ss"};
    @Dropdown(
            name = "counter hud format",
            options = {"H:mm:ss.SSS", "HH:mm:ss.SSS", "H:mm", "H:mm:ss"} // copy of formatting options, not constant so beans dawg
    )
    public static int counterHudFormat = 0;
    @Override
    protected String getText(boolean example) {
        return DurationFormatUtils.formatDuration(tickCounter * 50, options[counterHudFormat]);
    }

    public void pauseResumeTimer() {
        paused = !paused;
    }

    public void reset() {
        tickCounter = 0;
        paused = true;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if ((event.phase == TickEvent.Phase.END) && !paused) {
            tickCounter++;
        }
    }
}
