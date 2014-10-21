/* @java.file.header */

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.grid.kernal.managers.security;

import org.gridgain.grid.*;
import org.gridgain.grid.kernal.managers.*;
import org.gridgain.grid.security.*;
import org.gridgain.grid.spi.authentication.*;
import org.jetbrains.annotations.*;

import java.util.*;

/**
 * This interface defines a grid authentication manager.
 */
public interface GridSecurityManager extends GridManager {
    /**
     * Checks if security check is enabled.
     *
     * @return {@code True} if authentication check is enabled.
     */
    public boolean securityEnabled();

    /**
     * Authenticates grid node with it's attributes via underlying {@link GridAuthenticationSpi}s.
     *
     * @param node Node id to authenticate.
     * @param cred Security credentials.
     * @return {@code True} if succeeded, {@code false} otherwise.
     * @throws GridException If error occurred.
     */
    public GridSecurityContext authenticateNode(GridNode node, GridSecurityCredentials cred) throws GridException;

    /**
     * Gets flag indicating whether all nodes or coordinator only should run the authentication for joining node.
     *
     * @return {@code True} if all nodes should run authentication process, {@code false} otherwise.
     */
    public boolean isGlobalNodeAuthentication();

    /**
     * Authenticates subject via underlying {@link GridAuthenticationSpi}s.
     *
     * @param ctx Authentication context.
     * @return {@code True} if succeeded, {@code false} otherwise.
     * @throws GridException If error occurred.
     */
    public GridSecurityContext authenticate(GridAuthenticationContext ctx) throws GridException;

    /**
     * Gets collection of authenticated nodes.
     *
     * @return Collection of authenticated nodes.
     * @throws GridException If error occurred.
     */
    public Collection<GridSecuritySubject> authenticatedSubjects() throws GridException;

    /**
     * Gets authenticated node subject.
     *
     * @param subjId Subject ID.
     * @return Security subject.
     * @throws GridException If error occurred.
     */
    public GridSecuritySubject authenticatedSubject(UUID subjId) throws GridException;

    /**
     * Authorizes grid operation.
     *
     * @param name Cache name or task class name.
     * @param perm Permission to authorize.
     * @param securityCtx Optional security context.
     * @throws GridSecurityException If security check failed.
     */
    public void authorize(String name, GridSecurityPermission perm, @Nullable GridSecurityContext securityCtx)
        throws GridSecurityException;

    /**
     * Callback invoked when subject session got expired.
     *
     * @param subjId Subject ID.
     */
    public void onSessionExpired(UUID subjId);
}
