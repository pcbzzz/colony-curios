package dad.dab.colony_curios.mixin;

import com.minecolonies.api.items.ModItems;
import com.minecolonies.core.client.render.worldevent.WorldEventContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosApi;

@Mixin(targets = "com.minecolonies.core.client.render.worldevent.ColonyBlueprintRenderer$BuildGoggles")
public class BuildGoggleMixin {

    @Inject(method = "isEnabled", at = @At("RETURN"), cancellable = true)
    public void isEnabled(WorldEventContext ctx, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            CuriosApi.getCuriosInventory(ctx.clientPlayer).ifPresent(inv -> {
                if (inv.isEquipped(ModItems.buildGoggles)) {
                    cir.setReturnValue(true);
                }
            });
        }
    }
}
