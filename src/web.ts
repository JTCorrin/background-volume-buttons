import { WebPlugin } from '@capacitor/core';

import type { BackgroundVolumeButtonListenerPlugin } from './definitions';

export class BackgroundVolumeButtonListenerWeb
  extends WebPlugin
  implements BackgroundVolumeButtonListenerPlugin {}
