// 
// Decompiled by Procyon v0.5.36
// 

package club.novola.zori.mixin.mixins;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import club.novola.zori.Zori;
import club.novola.zori.module.combat.Reach;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mixin({ PlayerControllerMP.class })
public class MixinPlayerControllerMP
{
    @Inject(method = { "getBlockReachDistance" }, at = { @At("HEAD") }, cancellable = true)
    private void reach(final CallbackInfoReturnable<Float> cir) {
        final Reach reach = (Reach)Zori.getInstance().moduleManager.getModuleByName("Reach");
        if (reach.isEnabled()) {
            cir.cancel();
            cir.setReturnValue(reach.distance.getValue());
        }
    }
}
