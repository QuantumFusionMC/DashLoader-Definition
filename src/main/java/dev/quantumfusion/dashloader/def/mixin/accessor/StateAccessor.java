package dev.quantumfusion.dashloader.def.mixin.accessor;

import com.google.common.collect.ImmutableMap;
import net.minecraft.state.State;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(State.class)
public interface StateAccessor<O, S> {

	@Accessor
	ImmutableMap<Property<?>, Comparable<?>> getEntries();

}
