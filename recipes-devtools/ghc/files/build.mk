SRC_HC_OPTS        = -H64m -O0 -fasm
GhcStage1HcOpts    = -O -fasm
#GhcStage2HcOpts    = -O0 -fasm
GhcLibHcOpts       = -O -fasm
SplitObjs          = NO
HADDOCK_DOCS       = NO
BUILD_DOCBOOK_HTML = NO
BUILD_DOCBOOK_PS   = NO
BUILD_DOCBOOK_PDF  = NO

INTEGER_LIBRARY = integer-simple

GhcLibWays = v dyn

Stage1Only = YES

NoFibWays =
STRIP :=
