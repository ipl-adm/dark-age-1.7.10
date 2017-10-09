/*
 *  Copyright (c) 2016-2017, Rubedo
 *  * http://thedarkage.ru
 */

package keelfy.darkdata.client.models.armor.kaermorhen;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import keelfy.darkdata.client.models.DAModelBase;
import keelfy.darkdata.client.models.DAModelRenderer;

@SideOnly(Side.CLIENT)
public final class KaerMorhenHand extends DAModelBase {

	private final DAModelRenderer Shape9;
	private final DAModelRenderer Shape10;
	private final DAModelRenderer Shape11;
	private final DAModelRenderer Shape12;
	private final DAModelRenderer Shape13;
	private final DAModelRenderer Shape21;
	private final DAModelRenderer Shape22;
	private final DAModelRenderer Shape24;
	private final DAModelRenderer Shape26;
	private final DAModelRenderer Shape28;
	private final DAModelRenderer Shape30;
	private final DAModelRenderer Shape31;
	private final DAModelRenderer Shape33;
	private final DAModelRenderer Shape35;
	private final DAModelRenderer Shape36;
	private final DAModelRenderer Shape41;
	private final DAModelRenderer Shape42;
	private final DAModelRenderer Shape43;
	private final DAModelRenderer Shape44;
	private final DAModelRenderer Shape49;
	private final DAModelRenderer Shape50;
	private final DAModelRenderer Shape53;
	private final DAModelRenderer Shape54;
	private final DAModelRenderer Shape55;
	private final DAModelRenderer Shape57;
	private final DAModelRenderer Shape60;
	private final DAModelRenderer Shape61;
	private final DAModelRenderer Shape64;
	private final DAModelRenderer leftarm;

	public KaerMorhenHand() {
		this.Shape9 = new DAModelRenderer(this, 0, 18);
		this.Shape9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape9.setRotationPoint(4.0F, -1.0F, -2.0F);
		this.setRotation(this.Shape9, 0.0F, 0.0F, 0.0F);
		this.Shape10 = new DAModelRenderer(this, 0, 18);
		this.Shape10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape10.setRotationPoint(7.0F, -1.0F, -2.0F);
		this.setRotation(this.Shape10, 0.0F, 0.0F, 0.0F);
		this.Shape11 = new DAModelRenderer(this, 0, 18);
		this.Shape11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		this.Shape11.setRotationPoint(7.0F, -0.1F, -3.6F);
		this.setRotation(this.Shape11, 0.4833219F, 0.0F, 0.0F);
		this.Shape12 = new DAModelRenderer(this, 0, 18);
		this.Shape12.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		this.Shape12.setRotationPoint(4.0F, -0.1F, -3.6F);
		this.setRotation(this.Shape12, 0.4833219F, 0.0F, 0.0F);
		this.Shape13 = new DAModelRenderer(this, 0, 18);
		this.Shape13.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape13.setRotationPoint(4.0F, -0.1F, -2.6F);
		this.setRotation(this.Shape13, 0.0F, 1.570796F, 0.0F);
		this.Shape21 = new DAModelRenderer(this, 0, 18);
		this.Shape21.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		this.Shape21.setRotationPoint(4.0F, -1.0F, 2.0F);
		this.setRotation(this.Shape21, -0.4833166F, 0.0F, 0.0F);
		this.Shape22 = new DAModelRenderer(this, 0, 18);
		this.Shape22.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		this.Shape22.setRotationPoint(7.0F, -1.0F, 2.0F);
		this.setRotation(this.Shape22, -0.4833166F, 0.0F, 0.0F);
		this.Shape24 = new DAModelRenderer(this, 0, 18);
		this.Shape24.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape24.setRotationPoint(4.0F, -0.1F, 3.8F);
		this.setRotation(this.Shape24, 0.0F, 1.570796F, 0.0F);
		this.Shape26 = new DAModelRenderer(this, 0, 25);
		this.Shape26.addBox(0.0F, 0.0F, 0.0F, 2, 4, 1);
		this.Shape26.setRotationPoint(5.0F, 0.0F, -2.0F);
		this.setRotation(this.Shape26, 1.570796F, 0.0F, 0.0F);
		this.Shape28 = new DAModelRenderer(this, 0, 25);
		this.Shape28.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
		this.Shape28.setRotationPoint(5.0F, 0.6F, -3.0F);
		this.setRotation(this.Shape28, 1.966846F, 0.0F, 0.0F);
		this.Shape30 = new DAModelRenderer(this, 0, 25);
		this.Shape30.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
		this.Shape30.setRotationPoint(5.0F, -0.1F, 3.8F);
		this.setRotation(this.Shape30, -2.019206F, 0.0F, 0.0F);
		this.Shape31 = new DAModelRenderer(this, 0, 18);
		this.Shape31.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape31.setRotationPoint(8.0F, 1.0F, -2.0F);
		this.setRotation(this.Shape31, 0.0F, 0.0F, 0.0F);
		this.Shape33 = new DAModelRenderer(this, 0, 18);
		this.Shape33.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape33.setRotationPoint(8.0F, 4.0F, -2.0F);
		this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
		this.Shape35 = new DAModelRenderer(this, 0, 18);
		this.Shape35.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		this.Shape35.setRotationPoint(7.0F, 1.0F, -3.0F);
		this.setRotation(this.Shape35, 0.0F, 0.6320364F, 0.0F);
		this.Shape36 = new DAModelRenderer(this, 0, 18);
		this.Shape36.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		this.Shape36.setRotationPoint(7.0F, 4.0F, -3.0F);
		this.setRotation(this.Shape36, 0.0F, 0.6320364F, 0.0F);
		this.Shape41 = new DAModelRenderer(this, 0, 18);
		this.Shape41.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		this.Shape41.setRotationPoint(8.2F, 1.0F, 1.4F);
		this.setRotation(this.Shape41, 0.0F, -0.5667673F, 0.0F);
		this.Shape42 = new DAModelRenderer(this, 0, 18);
		this.Shape42.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
		this.Shape42.setRotationPoint(8.2F, 4.0F, 1.4F);
		this.setRotation(this.Shape42, 0.0F, -0.5667608F, 0.0F);
		this.Shape43 = new DAModelRenderer(this, 0, 18);
		this.Shape43.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape43.setRotationPoint(4.0F, 1.0F, -2.6F);
		this.setRotation(this.Shape43, 0.0F, 1.570796F, 0.0F);
		this.Shape44 = new DAModelRenderer(this, 0, 18);
		this.Shape44.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape44.setRotationPoint(4.0F, 4.0F, -2.6F);
		this.setRotation(this.Shape44, 0.0F, 1.570796F, 0.0F);
		this.Shape49 = new DAModelRenderer(this, 0, 18);
		this.Shape49.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape49.setRotationPoint(4.0F, 1.0F, 3.6F);
		this.setRotation(this.Shape49, 0.0F, 1.570796F, 0.0F);
		this.Shape50 = new DAModelRenderer(this, 0, 18);
		this.Shape50.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape50.setRotationPoint(4.0F, 4.0F, 3.6F);
		this.setRotation(this.Shape50, 0.0F, 1.570796F, 0.0F);
		this.Shape53 = new DAModelRenderer(this, 0, 18);
		this.Shape53.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape53.setRotationPoint(4.0F, 5.0F, -2.6F);
		this.setRotation(this.Shape53, 1.570796F, 1.570796F, 0.0F);
		this.Shape54 = new DAModelRenderer(this, 0, 18);
		this.Shape54.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4);
		this.Shape54.setRotationPoint(4.0F, 4.9F, 3.6F);
		this.setRotation(this.Shape54, 1.570796F, 1.570796F, 0.0F);
		this.Shape55 = new DAModelRenderer(this, 0, 25);
		this.Shape55.addBox(0.0F, 0.0F, 0.0F, 2, 4, 1);
		this.Shape55.setRotationPoint(8.0F, 2.0F, -2.0F);
		this.setRotation(this.Shape55, 1.570796F, 0.0F, 1.570796F);
		this.Shape57 = new DAModelRenderer(this, 0, 25);
		this.Shape57.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1);
		this.Shape57.setRotationPoint(5.0F, 2.0F, -2.6F);
		this.setRotation(this.Shape57, 3.141593F, 0.0F, 1.570796F);
		this.Shape60 = new DAModelRenderer(this, 0, 25);
		this.Shape60.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1);
		this.Shape60.setRotationPoint(5.0F, 2.0F, 3.6F);
		this.setRotation(this.Shape60, 3.141593F, 0.0F, 1.570796F);
		this.Shape61 = new DAModelRenderer(this, 0, 25);
		this.Shape61.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
		this.Shape61.setRotationPoint(7.0F, 2.0F, -2.9F);
		this.setRotation(this.Shape61, 2.249306F, 0.0F, 1.570796F);
		this.Shape64 = new DAModelRenderer(this, 0, 25);
		this.Shape64.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
		this.Shape64.setRotationPoint(8.1F, 2.0F, 3.5F);
		this.setRotation(this.Shape64, -2.039871F, 0.0F, 1.570796F);
		this.leftarm = new DAModelRenderer(this, 40, 16);
		this.leftarm.addBox(-1.0F, -2.0F, -2.0F, 4, 5, 4);
		this.leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.leftarm.setTextureSize(64, 32);
		this.leftarm.mirror = true;
		this.setRotation(this.leftarm, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public final void renderAll() {
		this.Shape9.render();
		this.Shape10.render();
		this.Shape11.render();
		this.Shape12.render();
		this.Shape13.render();
		this.Shape21.render();
		this.Shape22.render();
		this.Shape24.render();
		this.Shape26.render();
		this.Shape28.render();
		this.Shape30.render();
		this.Shape31.render();
		this.Shape33.render();
		this.Shape35.render();
		this.Shape36.render();
		this.Shape41.render();
		this.Shape42.render();
		this.Shape43.render();
		this.Shape44.render();
		this.Shape49.render();
		this.Shape50.render();
		this.Shape53.render();
		this.Shape54.render();
		this.Shape55.render();
		this.Shape57.render();
		this.Shape60.render();
		this.Shape61.render();
		this.Shape64.render();
		this.leftarm.render();
	}
}
