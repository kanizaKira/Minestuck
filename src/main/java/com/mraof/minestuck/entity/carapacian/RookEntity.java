package com.mraof.minestuck.entity.carapacian;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class RookEntity extends CarapacianEntity implements IMob
{
	protected RookEntity(EntityType<? extends RookEntity> type, EnumEntityKingdom kingdom, World world)
	{
		super(type, kingdom, world);
		this.experienceValue = 10;
	}
	
	public static RookEntity createProspitian(EntityType<? extends RookEntity> type, World world)
	{
		return new RookEntity(type, EnumEntityKingdom.PROSPITIAN, world);
	}
	
	public static RookEntity createDersite(EntityType<? extends RookEntity> type, World world)
	{
		return new RookEntity(type, EnumEntityKingdom.DERSITE, world);
	}
	
	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50D);
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
	}
	
	@Override
	protected void registerGoals()
	{
		super.registerGoals();
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, .4F, false));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, true, false, entity -> attackEntitySelector.isEntityApplicable(entity)));
	}

	public float getAttackStrength(Entity entity)
	{
		return 5;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		float damage = this.getAttackStrength(entity);
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
	}

//	@Override
//	protected void attackEntity(Entity entity, float par2)
//	{
//		if(this.attackTime <= 0	&& par2 < 2F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
//		{
//			this.attackTime = 20;
//			this.attackEntityAsMob(entity);
//		}
//	}
}