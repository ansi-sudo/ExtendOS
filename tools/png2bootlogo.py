#!/usr/bin/env python3
import glob, os, struct, sys
from PIL import Image, ImageOps

root = os.path.expanduser('~/extendos')
default = f'{root}/sources/yocto-superbird/meta-superbird/recipes-bsp/superbird-logo/files/bootup.bmp'
outdir = f'{root}/meta-extendos/recipes-bsp/superbird-logo/files'
out = f'{outdir}/bootup.bmp'

if len(sys.argv) > 1:
    src = sys.argv[1] if os.path.isabs(sys.argv[1]) else f'{outdir}/{sys.argv[1]}'
else:
    pngs = glob.glob(f'{outdir}/*.png')
    if len(pngs) != 1:
        sys.exit(f'expected exactly one .png in {outdir}, found {len(pngs)}: pass the name as an argument')
    src = pngs[0]
rot = int(sys.argv[2]) if len(sys.argv) > 2 else 270

hdr = bytearray(open(default, 'rb').read(138))
assert hdr[:2] == b'BM'
off, = struct.unpack_from('<I', hdr, 10)
w, h, planes, bpp, comp = struct.unpack_from('<iiHHI', hdr, 18)
assert off == 138 and bpp == 16 and w == 480 and abs(h) == 800, (off, w, h, bpp)
masks = struct.unpack_from('<III', hdr, 54) if comp == 3 else (0x7C00, 0x03E0, 0x001F)

def spec(m):
    s = (m & -m).bit_length() - 1
    return s, bin(m >> s).count('1')
(rs, rb), (gs, gb), (bs, bb) = map(spec, masks)

img = Image.open(src).convert('RGB')
if rot:
    img = img.rotate(rot, expand=True)
img = ImageOps.pad(img, (w, abs(h)), color=(0, 0, 0))
px = img.load()
rows = range(abs(h) - 1, -1, -1) if h > 0 else range(abs(h))
data = bytearray()
for y in rows:
    for x in range(w):
        r, g, b = px[x, y]
        v = ((r >> (8 - rb)) << rs) | ((g >> (8 - gb)) << gs) | ((b >> (8 - bb)) << bs)
        data += struct.pack('<H', v)

open(out, 'wb').write(bytes(hdr) + bytes(data))
print('converted', src, '->', out, len(hdr) + len(data), 'bytes')
