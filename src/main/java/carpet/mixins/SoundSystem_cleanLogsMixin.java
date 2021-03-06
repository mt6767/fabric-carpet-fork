package carpet.mixins;

import carpet.CarpetSettings;
import net.minecraft.client.sound.SoundSystem;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SoundSystem.class)
public class SoundSystem_cleanLogsMixin
{
    @Redirect(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", at = @At( remap = false,
            value = "INVOKE",
            target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;)V"
    ))
    private void toWarnOrNotToWarn(Logger logger, String message)
    {
        if (!CarpetSettings.cleanLogs) logger.warn(message);
    }
}
