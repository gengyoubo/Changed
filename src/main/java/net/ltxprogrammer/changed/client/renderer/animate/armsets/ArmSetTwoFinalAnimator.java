package net.ltxprogrammer.changed.client.renderer.animate.armsets;

import net.ltxprogrammer.changed.client.renderer.animate.HumanoidAnimator;
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.client.renderer.model.AdvancedHumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import org.jetbrains.annotations.NotNull;

public class ArmSetTwoFinalAnimator<T extends ChangedEntity, M extends AdvancedHumanoidModel<T>> extends HumanoidAnimator.Animator<T, M> {
    public final ModelPart leftArm;
    public final ModelPart rightArm;
    public final ModelPart leftArm2;
    public final ModelPart rightArm2;

    public ArmSetTwoFinalAnimator(ModelPart leftArm, ModelPart rightArm, ModelPart leftArm2, ModelPart rightArm2) {
        this.leftArm = leftArm;
        this.rightArm = rightArm;
        this.leftArm2 = leftArm2;
        this.rightArm2 = rightArm2;
    }

    @Override
    public HumanoidAnimator.AnimateStage preferredStage() {
        return HumanoidAnimator.AnimateStage.FINAL;
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        rightArm2.xRot = rightArm.xRot * 0.88F;
        rightArm2.yRot = rightArm.yRot * 0.88F;
        rightArm2.zRot = rightArm.zRot * 0.88F + 0.23f;
        leftArm2.xRot = leftArm.xRot * 0.88F;
        leftArm2.yRot = leftArm.yRot * 0.88F;
        leftArm2.zRot = leftArm.zRot * 0.88F - 0.23f;
    }
}
