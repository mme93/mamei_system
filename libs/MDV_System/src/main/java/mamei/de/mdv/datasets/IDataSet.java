package mamei.de.mdv.datasets;

import mamei.de.mdv.entity.attribute.Attribute;

public interface IDataSet {

   String asJSON();
   String asDataList();
   void addAttributeToEntity(Attribute attribute);
   void removeAttributeFromEntity(Attribute attribute);

}
