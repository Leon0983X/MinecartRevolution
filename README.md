MinecartRevolution
==================

MinecartRevolution is a modern Minecart-Plugin for Bukkit. It features simple functions for beginners and professional ones like an own english-like scripting language.
You can use it simple with Control-Signs and -Blocks and define the expression scripts. You can also write plugins for more complicated Controls.

There is no limit with MinecartRevolution.

For more information on the usage, check out the [wiki page](http://quartercode.com/wiki/index.php?title=MinecartRevolution) and the [BukkitDev page](http://dev.bukkit.org/server-mods/MinecartRevolution/).

License
-------

Copyright (c) 2013 QuarterCode <http://www.quartercode.com/>

MinecartRevolution may be used under the terms of the GNU General Public License (GPL) v3.0. See the LICENSE.md file or https://www.gnu.org/licenses/gpl-3.0.txt for details.

Compilation
-----------

We use maven to handle our dependencies and build, so you need the Java JDK and Maven for compiling the sourcecode.

* Download & install [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* Download & install [Maven 3](http://maven.apache.org/download.cgi).
* Check out this repository (clone or download).
* Navigate to the project folder of this repository which contains a `pom.xml` and run:

        mvn clean install

Builds
------

* MinecartRevolution is built by a [Jenkins job](http://ci.quartercode.com/job/MinecartRevolution/) on the QuarterCode Jenkins instance.
* Finished builds can be downloaded from the [BukkitDev page](http://dev.bukkit.org/server-mods/MinecartRevolution/) and the [QuarterCode DL website](http://quartercode.com/dl/projects/details?projectId=MinecartRevolution).
* Builds are also available on the [QuarterCode maven repository](http://repo.quartercode.com).
  In order to reference MinecartRevolution in another maven project, the following lines must be added to the project's pom:

        <repositories>
            ...
            <repository>
                <id>quartercode-repository</id>
                <url>http://repo.quartercode.com/content/groups/public/</url>
            </repository>
            ...
        </repositories>

        ...

        <dependencies>
            ...
            <dependency>
                <groupId>com.quartercode</groupId>
                <artifactId>minecartrevolution-package</artifactId>
                <version>...</version>
            </dependency>
            ...
        </dependencies>
