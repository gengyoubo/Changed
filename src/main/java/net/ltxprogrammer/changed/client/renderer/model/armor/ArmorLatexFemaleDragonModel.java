package net.ltxprogrammer.changed.client.renderer.model.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ltxprogrammer.changed.Changed;
import net.ltxprogrammer.changed.client.renderer.animate.AnimatorPresets;
import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.client.tfanimations.HelperModel;
import net.ltxprogrammer.changed.client.tfanimations.Limb;
import net.ltxprogrammer.changed.client.tfanimations.TransfurHelper;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArmorLatexFemaleDragonModel<T extends ChangedEntity> extends LatexHumanoidArmorModel<T, ArmorLatexFemaleDragonModel<T>> {
    public static final ArmorModelSet<ChangedEntity, ArmorLatexFemaleDragonModel<ChangedEntity>> MODEL_SET =
            ArmorModelSet.of(Changed.modResource("armor_latex_dragon_female_unified"), ArmorLatexFemaleDragonModel::createArmorLayer, ArmorLatexFemaleDragonModel::new);

    private final ModelPart Head;
    private final ModelPart Torso;
    private final ModelPart LeftLeg;
    private final ModelPart RightLeg;
    private final ModelPart LeftArm;
    private final ModelPart RightArm;
    private final ModelPart Tail;
    private final HumanoidAnimator<T, ArmorLatexFemaleDragonModel<T>> animator;

    public ArmorLatexFemaleDragonModel(ModelPart modelPart, ArmorModel model) {
        super(modelPart, model);
        this.Head = modelPart.getChild("Head");
        this.Torso = modelPart.getChild("Torso");
        this.LeftLeg = modelPart.getChild("LeftLeg");
        this.RightLeg = modelPart.getChild("RightLeg");
        this.LeftArm = modelPart.getChild("LeftArm");
        this.RightArm = modelPart.getChild("RightArm");
        this.Tail = Torso.getChild("Tail");

        var tailPrimary = Tail.getChild("TailPrimary");
        var tailSecondary = tailPrimary.getChild("TailSecondary");

        var leftLowerLeg = LeftLeg.getChild("LeftLowerLeg");
        var leftFoot = leftLowerLeg.getChild("LeftFoot");
        var rightLowerLeg = RightLeg.getChild("RightLowerLeg");
        var rightFoot = rightLowerLeg.getChild("RightFoot");

        animator = HumanoidAnimator.of(this).hipOffset(-1.5f)
                .addPreset(AnimatorPresets.dragonLike(Head, Torso, LeftArm, RightArm,
                        Tail, List.of(tailPrimary, tailSecondary),
                        LeftLeg, leftLowerLeg, leftFoot, leftFoot.getChild("LeftPad"), RightLeg, rightLowerLeg, rightFoot, rightFoot.getChild("RightPad")));
    }

    public static LayerDefinition createArmorLayer(ArmorModel layer) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        addUnifiedLegs(partdefinition, layer);

        PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, layer.dualDeformation), PartPose.offset(0.0F, -0.5F, 0.0F));

        addBreastplate(Torso, layer);

        PartDefinition Tail = Torso.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 11.0F, 0.0F));

        PartDefinition TailPrimary = Tail.addOrReplaceChild("TailPrimary", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Base_r1 = TailPrimary.addOrReplaceChild("Base_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, layer.altDeformation.extend(-0.3F)), PartPose.offsetAndRotation(0.0F, 0.4206F, 2.5285F, -2.0944F, 0.0F, 0.0F));

        PartDefinition TailSecondary = TailPrimary.addOrReplaceChild("TailSecondary", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 3.5F));

        PartDefinition Base_r2 = TailSecondary.addOrReplaceChild("Base_r2", CubeListBuilder.create().texOffs(0, 20).addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 6.0F, layer.altDeformation.extend(-0.1F)), PartPose.offsetAndRotation(0.0F, 1.2072F, 2.7389F, 2.7489F, 0.0F, 0.0F));

        PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, layer.dualDeformation), PartPose.offset(0.0F, -0.5F, 0.0F));

        PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, layer.dualDeformation), PartPose.offset(-5.0F, 1.5F, 0.0F));

        PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, layer.dualDeformation).mirror(false), PartPose.offset(5.0F, 1.5F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void prepareVisibility(EquipmentSlot armorSlot, ItemStack item) {
        super.prepareVisibility(armorSlot, item);
        if (armorSlot == EquipmentSlot.LEGS) {
            prepareUnifiedLegsForArmor(item, LeftLeg, RightLeg, Tail);
        }
    }

    @Override
    public void unprepareVisibility(EquipmentSlot armorSlot, ItemStack item) {
        super.unprepareVisibility(armorSlot, item);
        if (armorSlot == EquipmentSlot.LEGS) {
            prepareUnifiedLegsForArmor(item, LeftLeg, RightLeg, Tail);
        }
    }

    @Override
    public void renderForSlot(T entity, RenderLayerParent<? super T, ?> parent, ItemStack stack, EquipmentSlot slot, PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.pushPose();
        this.scaleForSlot(parent, slot, poseStack);

        switch (slot) {
            case HEAD -> Head.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            case CHEST -> {
                Torso.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
                LeftArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
                RightArm.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            }
            case LEGS -> {
                Torso.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
                LeftLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
                RightLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            }
            case FEET -> {
                LeftLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
                RightLeg.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
            }
        }

        poseStack.popPose();
    }

    @Override
    public @Nullable HelperModel getTransfurHelperModel(Limb limb) {
        if (limb == Limb.TORSO)
            return TransfurHelper.getFeminineTorso(this.armorModel);
        return super.getTransfurHelperModel(limb);
    }

    @Override
    public HumanoidAnimator<T, ArmorLatexFemaleDragonModel<T>> getAnimator() {
        return animator;
    }

    public ModelPart getArm(HumanoidArm arm) {
        return arm == HumanoidArm.LEFT ? this.LeftArm : this.RightArm;
    }

    public ModelPart getLeg(HumanoidArm leg) {
        return leg == HumanoidArm.LEFT ? this.LeftLeg : this.RightLeg;
    }

    public ModelPart getHead() {
        return this.Head;
    }

    public ModelPart getTorso() {
        return Torso;
    }
}
