package net.ltxprogrammer.changed.entity.beast;

import net.ltxprogrammer.changed.entity.*;
import net.ltxprogrammer.changed.util.Color3;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LatexSiameseCat extends ChangedEntity implements GenderedEntity {
    public LatexSiameseCat(EntityType<? extends LatexSiameseCat> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Override
    public Gender getGender() {
        return Gender.FEMALE;
    }

    @Override
    public TransfurMode getTransfurMode() {
        return TransfurMode.ABSORPTION;
    }

    @Override
    public Color3 getDripColor() {
        return Color3.fromInt(0xfdeae0);
    }

    @Override
    public Color3 getHairColor(int layer) {
        return Color3.fromInt(0xfdeae0);
    }

    public @Nullable List<HairStyle> getValidHairStyles() {
        return HairStyle.Collection.FEMALE.getStyles();
    }

    @Override
    public LatexType getLatexType() {
        return LatexType.NEUTRAL;
    }

    @Override
    public HairStyle getDefaultHairStyle() {
        return HairStyle.LONG_KEPT.get();
    }

    public Color3 getTransfurColor(TransfurCause cause) {
        return Color3.fromInt(0xfdeae0);
    }
}
