1.2.0
-----

### Additions
* Control signs can be attached to the side of the block the minecart rail is placed on.
* The global maxSpeed configuration setting for adjusting the maximal speed of all minecarts.

### Removals
* The maxSpeed configuration setting for the speed expression command.

### Fixes
* The created plugin jar no longer contains unnecessary third party libraries.
* Errors that occur during the execution of a java script line for parsing expression parameters are ignored.
* The y constant actually returns the y coordinate instead of the z one.
* Double expression constants are rounded to a sensible decimal place.
* Double expression constant values which do not have decimal places no longer show a decimal point.
* Expression constants have the same name as equivalent commands (e.g. "sp").

### Notes
* Maven will publish the sources and JavaDocs from this version onwards.

1.1.1
-----

### Additions
* Now using OddItem item aliases.

### Fixes
* Correct handling of invalid item aliases.
* Fixed chest, furnace and farm signs. 

1.1.0
-----

### Additions
* New update system using QuarterBukkit's ServerMods Query API.

### Fixes
* Fixed some information in the readme and project object model files.

1.0.0
-----

### Additions
* Support for new minecart types (TNT & hopper).
* New config structure for the global config.
* VehicleMetadataStorage for storing metadata values for any type of vehicle.
* Every container (chests, dispensers etc.) can now absorb colliding minecarts.

### Removals
* The punch action for changing a minecart's velocity manually.

### Fixes
* Now using QuarterBukkit's RedstoneToggleEvent.
* Overhauled the Direction utility.
