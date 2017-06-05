package com.chow.arch.netty.heartbeat;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * Created by Shelvin on 01/06/2017.
 */
public final class MarshallingCodeCFactory
{
    public static MarshallingDecoder buildMarshallingDecoder()
    {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration marshallingConfiguration = new MarshallingConfiguration();
        marshallingConfiguration.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, marshallingConfiguration);
        MarshallingDecoder decoder = new MarshallingDecoder(provider, 1024 * 1024 * 1);
        return decoder;
    }

    public static MarshallingEncoder buildMarshallingEncoder()
    {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration marshallingConfiguration = new MarshallingConfiguration();
        marshallingConfiguration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, marshallingConfiguration);
        MarshallingEncoder encoder = new MarshallingEncoder(provider);
        return encoder;
    }
}
