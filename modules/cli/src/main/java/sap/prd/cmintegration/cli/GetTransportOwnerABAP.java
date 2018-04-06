package sap.prd.cmintegration.cli;

import static java.lang.String.format;
import static sap.prd.cmintegration.cli.Commands.Helpers.getCommandName;

import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.cmclient.Transport;

/**
 * Command for retrieving the owner of a transport.
 */
@CommandDescriptor(name="get-transport-owner", type = BackendType.ABAP)
class GetTransportOwnerABAP extends TransportRelatedABAP {

    final static private Logger logger = LoggerFactory.getLogger(GetTransportOwnerABAP.class);

    GetTransportOwnerABAP(String host, String user, String password, String changeId, String transportId) {
        super(host, user, password, changeId, transportId);
    }

    @Override
    protected Predicate<Transport> getOutputPredicate() {
        return getOwner;
    }

    public final static void main(String[] args) throws Exception {
        logger.debug(format("%s called with arguments: '%s'.", GetTransportOwnerABAP.class.getSimpleName(), Commands.Helpers.getArgsLogString(args)));
        TransportRelatedSOLMAN.main(GetTransportOwnerABAP.class, args,
                format("%s -%s <%s>", getCommandName(GetTransportOwnerABAP.class),
                Opts.TRANSPORT_ID.getOpt(), Opts.TRANSPORT_ID.getArgName()),
                format("Returns the owner of the transport represented by <%s>.", Opts.TRANSPORT_ID.getArgName()));
    }
}
