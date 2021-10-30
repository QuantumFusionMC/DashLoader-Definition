package dev.quantumfusion.dashloader.def.data.model;

import dev.quantumfusion.dashloader.core.api.annotation.DashDependencies;
import dev.quantumfusion.dashloader.core.api.annotation.DashObject;
import dev.quantumfusion.dashloader.core.registry.DashRegistryReader;
import dev.quantumfusion.dashloader.core.registry.DashRegistryWriter;
import dev.quantumfusion.dashloader.def.data.model.components.DashWeightedModelEntry;
import dev.quantumfusion.dashloader.def.mixin.accessor.WeightedBakedModelAccessor;
import dev.quantumfusion.hyphen.scan.annotations.Data;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.WeightedBakedModel;
import net.minecraft.util.collection.Weighted;

import java.util.ArrayList;
import java.util.List;

@Data
@DashObject(WeightedBakedModel.class)
@DashDependencies({DashBasicBakedModel.class, DashBuiltinBakedModel.class})
public final class DashWeightedBakedModel implements DashModel {
	public final List<DashWeightedModelEntry> models;

	public DashWeightedBakedModel(List<DashWeightedModelEntry> models) {
		this.models = models;
	}

	public DashWeightedBakedModel(WeightedBakedModel model, DashRegistryWriter writer) {
		this.models = new ArrayList<>();
		for (var weightedModel : ((WeightedBakedModelAccessor) model).getModels())
			this.models.add(new DashWeightedModelEntry(weightedModel, writer));
	}

	@Override
	public WeightedBakedModel export(DashRegistryReader reader) {
		var modelsOut = new ArrayList<Weighted.Present<BakedModel>>();
		for (DashWeightedModelEntry model : models) modelsOut.add(model.export(reader));
		return new WeightedBakedModel(modelsOut);
	}
}
