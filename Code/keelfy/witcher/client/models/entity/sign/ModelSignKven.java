
package keelfy.witcher.client.models.entity.sign;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSignKven extends ModelBase {

	ModelRenderer Shape1;

	public ModelSignKven() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.Shape1 = new ModelRenderer(this, 0, 0);
		this.Shape1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
		this.Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Shape1.setTextureSize(64, 32);
		this.Shape1.mirror = true;
		this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
	}

	public void render(float f5) {
		this.Shape1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
