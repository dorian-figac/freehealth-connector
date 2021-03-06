package org.taktik.connector.business.chapterIV.session.impl;

import org.taktik.connector.business.chapterIV.exception.ChapterIVBusinessConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.session.ImplementationClassFactory;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;

public class ChapterIVServiceImplementationFactory extends ImplementationClassFactory {
   public <T> T createImplementationClass(Class<T> clazz, SessionValidator sessionValidator, EhealthReplyValidator replyValidator, String... additionalParameters) throws ChapterIVBusinessConnectorException, TechnicalConnectorException {
      if (clazz.equals(ChapterIVServiceImpl.class) && additionalParameters.length == 0) {
         return (T) new ChapterIVServiceImpl(sessionValidator, replyValidator);
      } else {
         throw new UnsupportedOperationException("class " + clazz + " not supported");
      }
   }
}
